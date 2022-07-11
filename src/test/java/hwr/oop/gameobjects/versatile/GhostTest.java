package hwr.oop.gameobjects.versatile;

import hwr.oop.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GhostTest {
    private Player player;
    private Ghost ghost;

    @BeforeEach
    void setUp() {
        Position playerPos = new Position();
        player = new Player(playerPos, 9);
        Position ghostPos = new Position();
        ghost = new Ghost(ghostPos, player);
    }

    @Test
    void Ghost_setStartPosition_setsStartPosition() {
        Position startPos = new Position(3, 3);
        ghost.setPosition(startPos);
        assertThat(ghost.getPosition()).isEqualTo(startPos);
    }

    @Test
    void Ghost_moveByAmount_movesByAmount() {
        Position startPos = new Position(3, 3);
        ghost.setPosition(startPos);

        Position amount = new Position(1, 1);
        ghost.moveByAmount(amount);

        Position expectedPos = new Position(4, 4);
        Position ghostPos = ghost.getPosition();

        assertThat(ghostPos).isEqualTo(expectedPos);
    }

    @Test
    void Ghost_IsAtPlayer_returnsTrue() {
        player.moveByAmount(new Position(0, 3));
        ghost.setPosition(new Position(0, 3));
        boolean isAtPlayer = ghost.ghostIsAtPlayer();
        assertThat(isAtPlayer).isTrue();
    }

    @Test
    void Ghost_IsAtPlayer_returnsFalse() {
        player.moveByAmount(new Position(0, 3));
        ghost.setPosition(new Position(3, 4));
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
    void Ghost_goesLeftTowardsPosition() {
        ghost.setPosition(new Position(5, 0));
        ghost.moveTowardsPosition(new Position(3, 0));

        Position ghostPos = ghost.getPosition();
        Position expectedPos = new Position(4, 0);

        assertThat(ghostPos).isEqualTo(expectedPos);
    }

    @Test
    void Ghost_goesRightTowardsPosition() {
        ghost.setPosition(new Position(3, 0));
        ghost.moveTowardsPosition(new Position(5, 0));

        Position ghostPos = ghost.getPosition();
        Position expectedPos = new Position(4, 0);

        assertThat(ghostPos).isEqualTo(expectedPos);
    }

    @Test
    void Ghost_goesUpTowardsPlayer() {
        ghost.setPosition(new Position(0, 5));
        ghost.moveTowardsPosition(new Position(0, 3));

        Position ghostPos = ghost.getPosition();
        Position expectedPos = new Position(0, 4);

        assertThat(ghostPos).isEqualTo(expectedPos);
    }

    @Test
    void Ghost_goesDownTowardsPlayer() {
        ghost.setPosition(new Position(0, 3));
        ghost.moveTowardsPosition(new Position(0, 5));

        Position ghostPos = ghost.getPosition();
        Position expectedPos = new Position(0, 4);

        assertThat(ghostPos).isEqualTo(expectedPos);
    }
}

