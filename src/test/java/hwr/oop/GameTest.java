package hwr.oop;

import hwr.oop.gameobjects.fixed.FieldObject;
import hwr.oop.gameobjects.fixed.Nothing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.StringWriter;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GameTest {

    @Nested
    class GameTests{
        Game game;
        @BeforeEach
        void setUp() {
            game = new Game(10);
        }

        @Test
        void Game_printGameStateToConsole_printsEmptyFieldOnStart() {
            // TODO: Rewrite Test when Field gets randomly generated on Start
            String expectedGameState =
                    "|==========|\n" +
                    "|          |\n".repeat(10) +
                    "|==========|";

            String printed = game.printGameStateToConsole();
            assertThat(printed).isEqualTo(expectedGameState);
        }
    }

    @Nested
    class PositionTests{
        @Test
        void Position_add_addsAmountToPosition() {
            Position position = new Position();
            position.add(new Position(1,1));
            assertThat(position).isEqualTo(new Position(1,1));
        }
    }
}
