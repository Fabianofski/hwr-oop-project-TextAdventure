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
    void Player_moveByPositionAmount_movesToNewPosition() {
        Position amount = new Position(3, 3);
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
    void Player_turnPlayer_looksInRightDirection() {
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
    void Player_givePlayerKey_playerNowHasKey() {
        player.giveKey();
        boolean hasKey = player.hasKey();

        assertThat(hasKey).isTrue();
    }

    @Test
    void Player_setPosition() {
        Position expectedPos = new Position(2, 5);
        player.setPosition(new Position(2, 5));
        assertThat(player.getPosition()).isEqualTo(expectedPos);
    }
}

