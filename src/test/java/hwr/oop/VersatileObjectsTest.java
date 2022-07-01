package hwr.oop;

import hwr.oop.gameobjects.fixed.FixedObject;
import hwr.oop.gameobjects.fixed.Nothing;
import hwr.oop.gameobjects.versatile.Ghost;
import hwr.oop.gameobjects.versatile.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class VersatileObjectsTest {

    private Player player;
    private Ghost ghost;
    private IIOHandler ioHandler;
    @BeforeEach
    void setUp() {
        ByteArrayInputStream input = new ByteArrayInputStream("Nothing".getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        ioHandler = new IOHandler(input, new PrintStream(output));

        FixedObject[][] testLevel = new FixedObject[9][9];
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                testLevel[x][y] = new Nothing(ioHandler);
            }
        }

        Position playerPos = new Position();
        player = new Player(playerPos, testLevel.length);
        Position ghostPos = new Position();
        ghost = new Ghost(ghostPos, player);
    }

    @Nested
    class GhostTests{
        @Test
        void Ghost_setStartPosition_setsStartPosition() {
            Position startPos = new Position(3,3);
            ghost.setStartPosition(startPos);
            assertThat(ghost.getPosition()).isEqualTo(startPos);
        }

        @Test
        void Ghost_moveByAmount_movesByAmount(){
            Position startPos = new Position(3,3);
            ghost.setStartPosition(startPos);

            Position amount = new Position(1,1);
            ghost.moveByAmount(amount);

            Position expectedPos = new Position(4,4);
            Position ghostPos = ghost.getPosition();

            assertThat(ghostPos).isEqualTo(expectedPos);
        }

        @Test
        void Ghost_IsAtPlayer_returnsTrue() {
            player.moveByAmount(new Position(0,3));
            ghost.setStartPosition(new Position(0,3));
            boolean isAtPlayer = ghost.ghostIsAtPlayer();
            assertThat(isAtPlayer).isTrue();
        }

        @Test
        void Ghost_IsAtPlayer_returnsFalse() {
            player.moveByAmount(new Position(0,3));
            ghost.setStartPosition(new Position(3,4));
            boolean isAtPlayer = ghost.ghostIsAtPlayer();
            assertThat(isAtPlayer).isFalse();
        }

        @Test
        void Ghost_hitPlayerAndDealDamage_DealsDamage() {
            int lives = player.getLives();
            ghost.hitPlayerAndDealDamage();
            assertThat(lives - 1).isEqualTo(player.getLives());
        }

        @Test
        void Ghost_goesLeftTowardsPosition(){
            ghost.setStartPosition(new Position(5,0));
            ghost.moveTowardsPosition(new Position(3,0));

            Position ghostPos = ghost.getPosition();
            Position expectedPos = new Position(4,0);

            assertThat(ghostPos).isEqualTo(expectedPos);
        }

        @Test
        void Ghost_goesRightTowardsPosition(){
            ghost.setStartPosition(new Position(3,0));
            ghost.moveTowardsPosition(new Position(5,0));

            Position ghostPos = ghost.getPosition();
            Position expectedPos = new Position(4,0);

            assertThat(ghostPos).isEqualTo(expectedPos);
        }

        @Test
        void Ghost_goesUpTowardsPlayer(){
            ghost.setStartPosition(new Position(0,5));
            ghost.moveTowardsPosition(new Position(0,3));

            Position ghostPos = ghost.getPosition();
            Position expectedPos = new Position(0,4);

            assertThat(ghostPos).isEqualTo(expectedPos);
        }

        @Test
        void Ghost_goesDownTowardsPlayer(){
            ghost.setStartPosition(new Position(0,3));
            ghost.moveTowardsPosition(new Position(0,5));

            Position ghostPos = ghost.getPosition();
            Position expectedPos = new Position(0,4);

            assertThat(ghostPos).isEqualTo(expectedPos);
        }
    }

    @Nested
    class PlayerTests{

        @Test
        void Player_moveByPositionAmount_movesToNewPosition() {
            Position amount = new Position(3,3);
            player.moveByAmount(amount);
            assertThat(player.getPosition()).isEqualTo(amount);
        }

        @Test
        void Player_moveByIntAmount_movesToNewPosition() {
            int amount = 3;
            String viewDirection = player.getObjectIcon();
            assertThat(viewDirection).isEqualTo("V");

            Position startingPos = player.getPosition();
            Position expectedPos = new Position(startingPos.getX(), startingPos.getY() + amount);

            player.moveByAmount(amount);
            Position playerPos = player.getPosition();

            assertThat(playerPos).isEqualTo(expectedPos);
        }

        @Test
        void Player_harmPlayer_dealsDamageToPlayer() {
            int lives = player.getLives();
            player.harmPlayer(2);
            assertThat(player.getLives()).isEqualTo(lives - 2);
        }

        @Test
        void Player_isDead_playerIsDeadWhenDealtEnoughDamage() {
            int lives = player.getLives();
            player.harmPlayer(lives);
            boolean isDead = player.isDead();
            assertThat(isDead).isTrue();
        }

        @Test
        void Player_isDead_playerIsAliveWhenNotDealtEnoughDamage() {
            int lives = player.getLives();
            player.harmPlayer(lives - 1);
            boolean isDead = player.isDead();
            assertThat(isDead).isFalse();
        }

        @Test
        void Player_turnPlayerRight_looksInRightDirection() {
            String icon = player.getObjectIcon();
            assertThat(icon).isEqualTo("V");

            player.turn(true);
            icon = player.getObjectIcon();
            assertThat(icon).isEqualTo("<");

            player.turn(true);
            icon = player.getObjectIcon();
            assertThat(icon).isEqualTo("^");

            player.turn(true);
            icon = player.getObjectIcon();
            assertThat(icon).isEqualTo(">");
        }

        @Test
        void Player_turnPlayerLeft_looksInRightDirection() {
            String icon = player.getObjectIcon();
            assertThat(icon).isEqualTo("V");

            player.turn(false);
            icon = player.getObjectIcon();
            assertThat(icon).isEqualTo(">");

            player.turn(false);
            icon = player.getObjectIcon();
            assertThat(icon).isEqualTo("^");

            player.turn(false);
            icon = player.getObjectIcon();
            assertThat(icon).isEqualTo("<");
        }

        @Test
        void Player_givePlayerKey_playerNowHasKey() {
            player.giveKey();
            boolean hasKey = player.hasKey();

            assertThat(hasKey).isTrue();
        }
    }

}
