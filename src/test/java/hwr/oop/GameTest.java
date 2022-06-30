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
            "0  1  2  3  4  5  6  7  8  9\n" +
            "1  _  _  _  _  _  _  _  _  _  \n" +
            "2  _  _  _  _  _  _  _  _  _  \n" +
            "3  _  _  _  _  _  _  _  _  _  \n" +
            "4  _  _  G  _  _  _  _  _  _  \n" +
            "5  _  _  _  _  _  _  _  _  _  \n" +
            "6  _  _  _  _  _  V  _  _  _  \n" +
            "7  _  _  _  _  _  Π  _  _  _  \n" +
            "8  _  _  _  _  _  _  _  _  _  \n" +
            "9  _  _  _  _  _  _  _  _  _  \n";

            String printed = ioHandler.getOutputBuffer();
            assertThat(printed).isEqualTo(expectedGameState);
        }

        @Test
        void proceed_playerMovesOnDoor_isFilledWithGameFieldAndDoorEvent() {
            String expectedGameState =
                    "0  1  2  3  4  5  6  7  8  9\n" +
                    "1  _  _  _  _  _  _  _  _  _  \n" +
                    "2  _  _  _  _  _  _  _  _  _  \n" +
                    "3  _  _  _  _  _  _  _  _  _  \n" +
                    "4  _  _  _  _  _  _  _  _  _  \n" +
                    "5  _  _  G  _  _  _  _  _  _  \n" +
                    "6  _  _  _  _  _  _  _  _  _  \n" +
                    "7  _  _  _  _  _  V  _  _  _  \n" +
                    "8  _  _  _  _  _  _  _  _  _  \n" +
                    "9  _  _  _  _  _  _  _  _  _  \n" +
                    "\nYou don't have a Key to open the door.";
            ioHandler.clearOutputBuffer();

            game.proceed(1);

            String printed = ioHandler.getOutputBuffer();
            assertThat(printed).isEqualTo(expectedGameState);
        }

        @Test
        void proceed_playerTurnsRight_isFilledWithStartingFieldAndTurnedPlayer() {
            String expectedGameState =
                    "0  1  2  3  4  5  6  7  8  9\n" +
                    "1  _  _  _  _  _  _  _  _  _  \n" +
                    "2  _  _  _  _  _  _  _  _  _  \n" +
                    "3  _  _  _  _  _  _  _  _  _  \n" +
                    "4  _  _  G  _  _  _  _  _  _  \n" +
                    "5  _  _  _  _  _  _  _  _  _  \n" +
                    "6  _  _  _  _  _  <  _  _  _  \n" +
                    "7  _  _  _  _  _  Π  _  _  _  \n" +
                    "8  _  _  _  _  _  _  _  _  _  \n" +
                    "9  _  _  _  _  _  _  _  _  _  \n" +
                            "\nNothing happens!";
            ioHandler.clearOutputBuffer();

            game.proceed("Right");

            String printed = ioHandler.getOutputBuffer();
            assertThat(printed).isEqualTo(expectedGameState);
        }

        @Test
        void gameOver_ghostIsAtPlayer_gameIsOver() {

            game.getGhost().moveByAmount(new Position(3, 2));
            boolean gameOver = game.gameOver();

            assertThat(gameOver).isTrue();
        }

        @Test
        void gameOver_ghostIsNotAtPlayer_gameIsNotOver() {
            boolean gameOver = game.gameOver();

            assertThat(gameOver).isFalse();
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

        @Test
        void Position_distance(){
            Position position = new Position(1,1);
            int distanceA = position.distance(new Position(2,2));
            assertThat(distanceA).isEqualTo(2);
            int distanceB = position.distance(new Position(5,6));
            assertThat(distanceB).isEqualTo(9);

        }
    }
}
