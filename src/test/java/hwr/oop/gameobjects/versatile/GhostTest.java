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
    void getObjectIcon_iconIsLetterG() {
        String icon = ghost.getObjectIcon();
        assertThat(icon).isEqualTo("G");
    }

    @Test
    void setPosition_setGhostPositionToNewPosition_ghostMovedToNewPosition() {
        Position newPosition = new Position(3, 3);
        ghost.setPosition(newPosition);
        assertThat(ghost.getPosition()).isEqualTo(newPosition);
    }

    @Test
    void moveByAmount_moveGhostByOneDiagonally_ghostMovedToNewPosition() {
        Position amount = new Position(1, 1);
        ghost.moveByAmount(amount);

        Position expectedPos = new Position(1, 1);
        Position ghostPos = ghost.getPosition();

        assertThat(ghostPos).isEqualTo(expectedPos);
    }

    @Test
    void isAtPlayer_ghostHasSamePositionAsPlayer_ghostIsAtPlayer() {
        player.moveByAmount(new Position(0, 3));
        ghost.setPosition(new Position(0, 3));
        boolean isAtPlayer = ghost.ghostIsAtPlayer();
        assertThat(isAtPlayer).isTrue();
    }

    @Test
    void isAtPlayer_ghostHasOtherPositionAsPlayer_ghostIsNotAtPlayer() {
        player.moveByAmount(new Position(0, 3));
        ghost.setPosition(new Position(3, 4));
        boolean isAtPlayer = ghost.ghostIsAtPlayer();
        assertThat(isAtPlayer).isFalse();
    }

    @Test
    void hitPlayerAndDealDamage_ghostHitsThePlayer_playerNowLostOneLife() {
        int lives = player.getLives();
        ghost.hitPlayerAndDealDamage();
        assertThat(lives - 1).isEqualTo(player.getLives());
    }

    @Test
    void moveTowardsPosition_ghostStandsRightOfPosition_ghostGoesLeft() {
        ghost.setPosition(new Position(5, 0));
        ghost.moveTowardsPosition(new Position(3, 0));

        Position ghostPos = ghost.getPosition();
        Position expectedPos = new Position(4, 0);

        assertThat(ghostPos).isEqualTo(expectedPos);
    }

    @Test
    void moveTowardsPosition_ghostStandsLeftOfPosition_ghostGoesRight() {
        ghost.setPosition(new Position(3, 0));
        ghost.moveTowardsPosition(new Position(5, 0));

        Position ghostPos = ghost.getPosition();
        Position expectedPos = new Position(4, 0);

        assertThat(ghostPos).isEqualTo(expectedPos);
    }

    @Test
    void moveTowardsPosition_ghostStandsBeneathOfPosition_ghostGoesUp() {
        ghost.setPosition(new Position(0, 5));
        ghost.moveTowardsPosition(new Position(0, 3));

        Position ghostPos = ghost.getPosition();
        Position expectedPos = new Position(0, 4);

        assertThat(ghostPos).isEqualTo(expectedPos);
    }

    @Test
    void moveTowardsPosition_ghostStandsAboveOfPosition_ghostGoesDown() {
        ghost.setPosition(new Position(0, 3));
        ghost.moveTowardsPosition(new Position(0, 5));

        Position ghostPos = ghost.getPosition();
        Position expectedPos = new Position(0, 4);

        assertThat(ghostPos).isEqualTo(expectedPos);
    }
}

