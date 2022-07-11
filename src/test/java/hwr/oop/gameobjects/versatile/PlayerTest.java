package hwr.oop.gameobjects.versatile;

import hwr.oop.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        Position playerPos = new Position();
        player = new Player(playerPos, 9);
    }

    @Test
    void moveByPositionAmount_addAmountToCurrentPosition_movesToNewPosition() {
        Position amount = new Position(3, 3);
        player.moveByAmount(amount);
        assertThat(player.getPosition()).isEqualTo(amount);
    }

    @Test
    void moveByIntAmount_playerLooksDownAndMoves_playerMoves3TilesBelowStartingPos() {
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
    void harmPlayer_playerGetsHarmed_playerNowLostOneLife() {
        int lives = player.getLives();
        player.harmPlayer(2);
        assertThat(player.getLives()).isEqualTo(lives - 2);
    }

    @Test
    void isDead_playerHasNoLivesLeft_playerIsDead() {
        int lives = player.getLives();
        player.harmPlayer(lives);
        boolean isDead = player.isDead();
        assertThat(isDead).isTrue();
    }

    @Test
    void isDead_playerHasLivesLeft_playerIsNotDead() {
        int lives = player.getLives();
        player.harmPlayer(lives - 1);
        boolean isDead = player.isDead();
        assertThat(isDead).isFalse();
    }

    @Test
    void turnPlayer_getObjectIcon_playerTurnsInEveryDirection_returnsCorrectIconForEachDirection() {
        player.turn(1);
        String icon = player.getObjectIcon();
        assertThat(icon).isEqualTo("<");

        player.turn(2);
        icon = player.getObjectIcon();
        assertThat(icon).isEqualTo("^");

        player.turn(3);
        icon = player.getObjectIcon();
        assertThat(icon).isEqualTo(">");

        player.turn(4);
        icon = player.getObjectIcon();
        assertThat(icon).isEqualTo("V");
    }

    @Test
    void giveKey_playerGetsKey_playerNowHasKey() {
        player.giveKey();
        boolean hasKey = player.hasKey();

        assertThat(hasKey).isTrue();
    }

    @Test
    void setPosition_setPlayerPositionToNewPosition_playerMovedToNewPosition() {
        Position expectedPos = new Position(2, 5);
        player.setPosition(new Position(2, 5));
        assertThat(player.getPosition()).isEqualTo(expectedPos);
    }
}

