package hwr.oop;

import hwr.oop.gameobjects.fixed.Door;
import hwr.oop.gameobjects.fixed.FixedObject;
import hwr.oop.gameobjects.fixed.Nothing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GameTest {

    @Nested
    class GameTests{
        Game game;
        IIOHandler ioHandler;

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
            testLevel[5][6] = new Door(ioHandler);
            Position playerPos = new Position(5, 5);
            Position ghostPos = new Position(2, 3);
            game = new Game(testLevel, ioHandler, playerPos, ghostPos);
        }

        @Test
        void proceed_ioHandler_isFilledWithStartingField() {
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

            String printed = ioHandler.getOutputBuffer();
            assertThat(printed).isEqualTo(expectedGameState);
        }

        @Test
        void proceed_playerMovesOnDoor_isFilledWithGameFieldAndDoorEvent() {
            String expectedGameState =
                    "0123456789\n" +
                    "1         \n" +
                    "2         \n" +
                    "3         \n" +
                    "4  G      \n" +
                    "5         \n" +
                    "6         \n" +
                    "7     V   \n" +
                    "8         \n" +
                    "9         \n" +
                    "\nYou don't have a Key to open the door.";
            ioHandler.clearOutputBuffer();

            game.proceed(1);

            String printed = ioHandler.getOutputBuffer();
            assertThat(printed).isEqualTo(expectedGameState);
        }

        @Test
        void proceed_playerTurnsRight_isFilledWithStartingFieldAndTurnedPlayer() {
            String expectedGameState =
                    "0123456789\n" +
                            "1         \n" +
                            "2         \n" +
                            "3         \n" +
                            "4  G      \n" +
                            "5         \n" +
                            "6     <   \n" +
                            "7     Π   \n" +
                            "8         \n" +
                            "9         \n" +
                            "\nNothing happens!";
            ioHandler.clearOutputBuffer();

            game.proceed("Right");

            String printed = ioHandler.getOutputBuffer();
            assertThat(printed).isEqualTo(expectedGameState);
        }
    }

    @Nested
    class PositionTests{

        @Test
        void Position_hashCode_worksAsIntended() {
            Position position = new Position();
            Position samePosition = new Position();

            assertThat(position.hashCode()).isEqualTo(samePosition.hashCode());
        }

        @Test
        void Position_toString_worksAsIntended() {
            Position position = new Position();

            assertThat(position.toString()).isEqualTo("Position{x=0,y=0}");
        }

        @Test
        void Position_add_addsAmountToPosition() {
            Position position = new Position();
            position.add(new Position(1,1));
            assertThat(position).isEqualTo(new Position(1,1));
        }
    }
}
