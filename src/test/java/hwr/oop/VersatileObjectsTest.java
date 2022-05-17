package hwr.oop;

import hwr.oop.gameobjects.versatile.Ghost;
import hwr.oop.gameobjects.versatile.Player;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class VersatileObjectsTest {

    @Nested
    class GhostTests{
        private Player player;
        private Ghost ghost;

        @BeforeEach
        void beforeEach() {
            player = new Player(new Position());
            ghost = new Ghost(new Position(), player);
        }

        @Test
        void Ghost_moveByAmount_movesToNewPosition() {
            Position amount = new Position(3,3);
            ghost.moveByAmount(amount);
            assertThat(ghost.getPosition()).isEqualTo(amount);
        }

        @Test
        void Ghost_IsAtPlayer_returnsTrue() {
            player.moveByAmount(new Position(0,3));
            ghost.moveByAmount(new Position(0,3));
            boolean isAtPlayer = ghost.ghostIsAtPlayer();
            assertThat(isAtPlayer).isTrue();
        }

        @Test
        void Ghost_IsAtPlayer_returnsFalse() {
            player.moveByAmount(new Position(0,3));
            ghost.moveByAmount(new Position(3,4));
            boolean isAtPlayer = ghost.ghostIsAtPlayer();
            assertThat(isAtPlayer).isFalse();
        }

        @Test
        void Ghost_hitPlayerAndDealDamage_DealsDamage() {
            int lives = player.getLives();
            ghost.hitPlayerAndDealDamage();
            assertThat(lives - 1).isEqualTo(player.getLives());
        }
    }

    @Nested
    class PlayerTests{
        private Player player;
        private Ghost ghost;

        @BeforeEach
        void beforeEach() {
            player = new Player(new Position());
            ghost = new Ghost(new Position(), player);
        }

        @Test
        void Player_moveByAmount_movesToNewPosition() {
            Position amount = new Position(3,3);
            player.moveByAmount(amount);
            assertThat(player.getPosition()).isEqualTo(amount);
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
    }

}
