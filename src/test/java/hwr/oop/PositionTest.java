package hwr.oop;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PositionTest {
    @Test
    void hashCode_worksAsIntended() {
        Position position = new Position();
        Position samePosition = new Position();

        assertThat(position.hashCode()).isEqualTo(samePosition.hashCode());
    }

    @Test
    void toString_worksAsIntended() {
        Position position = new Position();

        assertThat(position.toString()).isEqualTo("Position{x=0,y=0}");
    }

    @Test
    void add_addsAmountToPosition_amountGetsAddedToPosition() {
        Position position = new Position();
        position.add(new Position(1, 1));
        assertThat(position).isEqualTo(new Position(1, 1));
    }

    @Test
    void distance_distanceBetweenTwoPositions_returnsCorrectDistance() {
        Position position = new Position(1, 1);
        int distance = position.distance(new Position(5, 6));
        assertThat(distance).isEqualTo(9);
    }

}
