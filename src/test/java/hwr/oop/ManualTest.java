package hwr.oop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class ManualTest {
    Game game;
    IIOHandler ioHandler;
    LevelManager levelManager;

    @BeforeEach
    void setUp() {
        ioHandler = new IOHandler(System.in, System.out);
        levelManager = new LevelManager(ioHandler);
        game = levelManager.getLevel(0);
        game.welcome();
    }

    @Test
    @Disabled
    void manualTest(){
        while (true){
            ioHandler.writeOutputAndClearBuffer();

            if (gameOverAndPlayerQuits()) break;

            askForPlayerAction();

            if (levelIsCompletedAndPlayerQuits()) break;
        }
    }


    private boolean gameOverAndPlayerQuits() {
        if(!game.gameOver()) return false;

        String tryAgain = ioHandler.requestStringInput("Do you wanna try again?\n Yes (1) / No (2)");
        if(tryAgain.equals("1")){
            game = levelManager.getLevel(0);
            setUp();
            ioHandler.writeOutputAndClearBuffer();
            return false;
        }
        else return true;
    }

    private void askForPlayerAction() {
        String decision = ioHandler.requestStringInput("Move (1) / Turn (2)?");
        if(decision.equals("1")) askForMoveInput();
        else if(decision.equals("2")) askForTurnInput();
        else ioHandler.addToOutputBuffer("Wrong Input!");
    }

    private void askForTurnInput() {
        int direction = ioHandler.requestIntegerInput("Left (1) / Up (2) / Right (3) / Down (4)?");
        if(direction < 1 || direction > 4) ioHandler.addToOutputBuffer("Wrong Input!");
        else game.proceedWithTurn(direction);
    }

    private void askForMoveInput() {
        int amount = ioHandler.requestIntegerInput("Move how many Steps? (1-3)");
        if(amount>3 || amount<0) ioHandler.addToOutputBuffer("You can only make 1-3 Steps!");
        else game.proceedWithMove(amount);
    }

    private boolean levelIsCompletedAndPlayerQuits() {
        if(!game.levelIsCompleted()) return false;

        String nextLevelDecision = ioHandler.requestStringInput
                ("You have won the Level!! Wanna go to the next Level?\n Yes (1) / No (2)");
        if(nextLevelDecision.equals("2")) return true;

        int nextLevelID = levelManager.getCurrentLevel() + 1;
        game = levelManager.getLevel(nextLevelID);
        if(game==null){
            ioHandler.addToOutputBuffer("You completed the game!");
            return true;
        }
        else {
            game.welcomeToNextLevel();
            return false;
        }
    }
}
