package hwr.oop;

import hwr.oop.gameobjects.fixed.Door;
import hwr.oop.gameobjects.fixed.FixedObject;
import hwr.oop.gameobjects.fixed.Nothing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GameTest {

    @Nested
    class GameTests{
        Game game;
        @BeforeEach
        void setUp() {
            FixedObject[][] testLevel = new FixedObject[9][9];
            for (int x = 0; x < 9; x++) {
                for (int y = 0; y < 9; y++) {
                    testLevel[x][y] = new Nothing();
                }
            }
            testLevel[5][6] = new Door();
            Position playerPos = new Position(5, 5);
            Position ghostPos = new Position(2, 3);
            game = new Game(testLevel, playerPos, ghostPos);
        }

        @Test
        void Game_printGameStateToConsole_printsEmptyFieldOnStart() {
            String expectedGameState =
                    "0123456789\n" +
                    "1         \n" +
                    "2         \n" +
                    "3         \n" +
                    "4  G      \n" +
                    "5         \n" +
                    "6     V   \n" +
                    "7     Π   \n" +
                    "8         \n" +
                    "9         \n";

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
