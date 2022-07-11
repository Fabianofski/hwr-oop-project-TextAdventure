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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GameTest {


    Game game;
    IIOHandler ioHandler;
    Player player;
    Ghost ghost;

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
        ghost = new Ghost(ghostPos, player);

        testLevel[5][6] = new Door(ioHandler, player);

        game = new Game(testLevel, ioHandler, player, ghost, 5);
    }

    @Test
    void welcome_gameIsFilledWithStartingField() {
        game.welcome();
        String expectedGameState =
                "0  1  2  3  4  5  6  7  8  9  \n" +
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
    void levelIsCompleted_playerHasOpenedDoor_levelIsCompleted() {
        player.setHasOpenedDoor(true);
        assertThat(game.levelIsCompleted()).isEqualTo(true);
    }

    @Test
    void levelIsCompleted_playerHasNotOpenedDoor_levelIsNotCompleted() {
        player.setHasOpenedDoor(false);
        assertThat(game.levelIsCompleted()).isEqualTo(false);
    }

    @Test
    void proceedWithMove_playerRunsAgainstWall_StopsBeforeWall() {
        FixedObject[][] testLevel = new FixedObject[9][9];
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                testLevel[x][y] = new Nothing(ioHandler);
            }
        }
        testLevel[5][6] = new Wall(ioHandler);
        game = new Game(testLevel, ioHandler, player, new Ghost(new Position(2, 4), player));

        game.proceedWithMove(3);
        Position expectedPosition = new Position(5, 5);
        assertThat(expectedPosition.toString()).isEqualTo(player.getPosition().toString());
    }

    @Test
    void proceedWithMove_playerMovesOnDoor_isFilledWithGameFieldAndDoorEvent() {
        String expectedGameState =
                "0  1  2  3  4  5  6  7  8  9  \n" +
                        "1  ?  ?  ?  ?  ?  ?  ?  ?  ?  \n" +
                        "2  ?  ?  ?  ?  ?  ?  ?  ?  ?  \n" +
                        "3  ?  ?  ?  ?  ?  ?  ?  ?  ?  \n" +
                        "4  ?  ?  ?  ?  ?  ?  ?  ?  ?  \n" +
                        "5  ?  ?  ?  ?  ?  ?  ?  ?  ?  \n" +
                        "6  ?  ?  ?  ?  _  _  _  ?  ?  \n" +
                        "7  ?  ?  ?  ?  _  V  _  ?  ?  \n" +
                        "8  ?  ?  ?  ?  _  _  _  ?  ?  \n" +
                        "9  ?  ?  ?  ?  ?  ?  ?  ?  ?  \n" +
                        "\n" +
                        "------------------------------------------------\n" +
                        "\n" +
                        "You don't have a Key to open the door.";
        ioHandler.clearOutputBuffer();

        game.proceedWithMove(1);

        String printed = ioHandler.getOutputBuffer();
        assertThat(printed).isEqualTo(expectedGameState);
    }

    @Test
    void proceedWithTurn_playerTurnsLeft_isFilledWithStartingFieldAndTurnedPlayer() {
        String expectedGameState =
                "0  1  2  3  4  5  6  7  8  9  \n" +
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

        game.proceedWithTurn(1);

        String printed = ioHandler.getOutputBuffer();
        assertThat(printed).isEqualTo(expectedGameState);
    }

    @Test
    void gameOver_ghostIsAtPlayer_gameIsOverAndGameOverMessageIsDisplayed() {
        ghost.setPosition(new Position(5, 5));

        boolean gameOver = game.gameOver();
        assertThat(gameOver).isTrue();

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
    void gameOver_playerHasNoLivesLeft_gameIsOver() {
        player.harmPlayer(3);
        assertThat(game.gameOver()).isEqualTo(true);
    }

    @Test
    void gameOver_ghostIsNotAtPlayer_gameIsNotOver() {
        boolean gameOver = game.gameOver();

        assertThat(gameOver).isFalse();
    }

    @Test
    void proceedWithMove_playerRuns3Tiles_getsHurtRunningWithSeedSetTo5() {
        game.proceedWithMove(3);

        boolean lessLife = player.getLives() < 3;
        assertThat(lessLife).isEqualTo(true);
    }

    @Test
    void welcomeToNextLevel_playerEntersNewLevel_newLevelFieldAndWelcomeMessageIsDisplayed() {
        game.welcomeToNextLevel();
        String output =
                "0  1  2  3  4  5  6  7  8  9  \n" +
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
