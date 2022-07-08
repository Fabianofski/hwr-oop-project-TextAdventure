package hwr.oop;

import hwr.oop.gameobjects.fixed.Door;
import hwr.oop.gameobjects.fixed.FixedObject;
import hwr.oop.gameobjects.fixed.Nothing;
import hwr.oop.gameobjects.fixed.Wall;
import hwr.oop.gameobjects.versatile.Ghost;
import hwr.oop.gameobjects.versatile.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GameTest {

    @Nested
    class GameTests{
        Game game;
        IIOHandler ioHandler;
        Player player;

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

            Position playerPos = new Position(5, 5);
            player = new Player(playerPos, testLevel.length);
            Position ghostPos = new Position(4, 4);
            Ghost ghost = new Ghost(ghostPos, player);

            testLevel[5][6] = new Door(ioHandler, player);

            game = new Game(testLevel, ioHandler, player, ghost);
        }

        @Test
        void gameStart_Field() {
            game.gameBegin();
            String expectedGameState =
            "0  1  2  3  4  5  6  7  8  9\n" +
                    "1  ?  ?  ?  ?  ?  ?  ?  ?  ?  \n" +
                    "2  ?  ?  ?  ?  ?  ?  ?  ?  ?  \n" +
                    "3  ?  ?  ?  ?  ?  ?  ?  ?  ?  \n" +
                    "4  ?  ?  ?  ?  ?  ?  ?  ?  ?  \n" +
                    "5  ?  ?  ?  ?  G  _  _  ?  ?  \n" +
                    "6  ?  ?  ?  ?  _  V  _  ?  ?  \n" +
                    "7  ?  ?  ?  ?  _  H  _  ?  ?  \n" +
                    "8  ?  ?  ?  ?  ?  ?  ?  ?  ?  \n" +
                    "9  ?  ?  ?  ?  ?  ?  ?  ?  ?  \n" +
                    "\n" +
                    "------------------------------------------------\n";

            String printed = ioHandler.getOutputBuffer();
            assertThat(printed).isEqualTo(expectedGameState);
        }
        @Test
        void gameWasWon_ifOpenedDoor(){
            player.setHasOpenedDoor(true);
            assertThat(game.GameWon()).isEqualTo(true);
        }
        @Test
        void gameNotWon_ifOpenedDoor(){
            player.setHasOpenedDoor(false);
            assertThat(game.GameWon()).isEqualTo(false);
        }
        @Test
        void player_cantRun_pastWall(){
            FixedObject[][] testLevel = new FixedObject[9][9];
            for (int x = 0; x < 9; x++) {
                for (int y = 0; y < 9; y++) {
                    testLevel[x][y] = new Nothing(ioHandler);
                }
            }
            testLevel[5][6] = new Wall(ioHandler);
            Position playerPos = new Position(5, 5);
            player = new Player(playerPos, testLevel.length);
            game = new Game(testLevel,ioHandler,player,new Ghost(new Position(2,4),player));
            game.proceed(3);
            Position expectedPosition = new Position(5,5);
           assertThat(expectedPosition.toString()).isEqualTo(player.getPosition().toString());

        }
        @Test
        void proceed_playerMovesOnDoor_isFilledWithGameFieldAndDoorEvent() {
            String expectedGameState =
                    "0  1  2  3  4  5  6  7  8  9\n" +
                            "1  ?  ?  ?  ?  ?  ?  ?  ?  ?  \n" +
                            "2  ?  ?  ?  ?  ?  ?  ?  ?  ?  \n" +
                            "3  ?  ?  ?  ?  ?  ?  ?  ?  ?  \n" +
                            "4  ?  ?  ?  ?  ?  ?  ?  ?  ?  \n" +
                            "5  ?  ?  ?  ?  ?  ?  ?  ?  ?  \n" +
                            "6  ?  ?  ?  ?  G  _  _  ?  ?  \n" +
                            "7  ?  ?  ?  ?  _  V  _  ?  ?  \n" +
                            "8  ?  ?  ?  ?  _  _  _  ?  ?  \n" +
                            "9  ?  ?  ?  ?  ?  ?  ?  ?  ?  \n" +
                            "\n" +
                            "------------------------------------------------\n" +
                            "\n" +
                            "You don't have a Key to open the door.";
            ioHandler.clearOutputBuffer();

            game.proceed(1);

            String printed = ioHandler.getOutputBuffer();
            assertThat(printed).isEqualTo(expectedGameState);
        }

        @Test
        void proceed_playerTurnsRight_isFilledWithStartingFieldAndTurnedPlayer() {
            String expectedGameState =
                    "0  1  2  3  4  5  6  7  8  9\n" +
                            "1  ?  ?  ?  ?  ?  ?  ?  ?  ?  \n" +
                            "2  ?  ?  ?  ?  ?  ?  ?  ?  ?  \n" +
                            "3  ?  ?  ?  ?  ?  ?  ?  ?  ?  \n" +
                            "4  ?  ?  ?  ?  ?  ?  ?  ?  ?  \n" +
                            "5  ?  ?  ?  ?  G  _  _  ?  ?  \n" +
                            "6  ?  ?  ?  ?  _  <  _  ?  ?  \n" +
                            "7  ?  ?  ?  ?  _  H  _  ?  ?  \n" +
                            "8  ?  ?  ?  ?  ?  ?  ?  ?  ?  \n" +
                            "9  ?  ?  ?  ?  ?  ?  ?  ?  ?  \n" +
                            "\n" +
                            "------------------------------------------------\n";
            ioHandler.clearOutputBuffer();

            game.proceed("1");

            String printed = ioHandler.getOutputBuffer();
            assertThat(printed).isEqualTo(expectedGameState);
        }
        @Test
        void gameOver_ghostIsAtPlayer_ghostShows() {
            game.getGhost().setStartPosition(new Position(5,5));
            game.gameOver();
            String expected = "     .-.\n" +
                    "   .'   `.\n" +
                    "   :g g   :\n" +
                    "   : o    `.\n" +
                    "  :         ``.\n" +
                    " :             `.\n" +
                    ":  :         .   `.\n" +
                    ":   :          ` . `.\n" +
                    " `.. :            `. ``;\n" +
                    "    `:;             `:'\n" +
                    "       :              `.\n" +
                    "        `.              `.     .\n" +
                    "          `'`'`'`---..,___`;.-'\n" +
                    "Sorry, you died!\n";
            assertThat(ioHandler.getOutputBuffer()).isEqualTo(expected);
        }
        @Test
        void gameOver_ghostIsAtPlayer_gameIsOver() {

            game.getGhost().setStartPosition(new Position(5,5));
            boolean gameOver = game.gameOver();

            assertThat(gameOver).isTrue();
        }

        @Test
        void gameOver_PlayerHasNoLive() {
            player.harmPlayer(3);
            assertThat(game.gameOver()).isEqualTo(true);
        }

        @Test
        void gameOver_ghostIsNotAtPlayer_gameIsNotOver() {
            boolean gameOver = game.gameOver();

            assertThat(gameOver).isFalse();
        }
        @Test
        void proceed_player_getsHurtIfRunning(){
            game.proceed(3);
            game.proceed(3);
            game.proceed(3);
            game.proceed(3);
            game.proceed(3);
            game.proceed(3);
            game.proceed(3);
            game.proceed(3);
            boolean lessLife = !(player.getLives()==3);
            assertThat(lessLife).isEqualTo(true);
        } //ist eine Zufallsmethode, anders kann mans nicht so gut testen:/

        @Test
        void nextLevel_Output(){
            ioHandler.clearOutputBuffer();
            game.nextLevel();
            String output =
                    "0  1  2  3  4  5  6  7  8  9\n" +
                            "1  ?  ?  ?  ?  ?  ?  ?  ?  ?  \n" +
                            "2  ?  ?  ?  ?  ?  ?  ?  ?  ?  \n" +
                            "3  ?  ?  ?  ?  ?  ?  ?  ?  ?  \n" +
                            "4  ?  ?  ?  ?  ?  ?  ?  ?  ?  \n" +
                            "5  ?  ?  ?  ?  G  _  _  ?  ?  \n" +
                            "6  ?  ?  ?  ?  _  V  _  ?  ?  \n" +
                            "7  ?  ?  ?  ?  _  H  _  ?  ?  \n" +
                            "8  ?  ?  ?  ?  ?  ?  ?  ?  ?  \n" +
                            "9  ?  ?  ?  ?  ?  ?  ?  ?  ?  \n" +
                            "\n" +
                            "------------------------------------------------\n" +
                            "\n" +
                            "------------------------------------------------\n" +
                            "You opened the door...And a new room opened before your eyes.";
            String printed = ioHandler.getOutputBuffer();
            assertThat(printed).isEqualTo(output);
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
