package hwr.oop;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class ManualTest {
    Game game;
    IIOHandler ioHandler;
    Levels level;

    @Test
    @Disabled
    void manualTestLevel1(){

        level = new Levels();
        game = level.getLevel(1);
        ioHandler = level.getIOHandler();
        int i = 1;
        while (true){

            ioHandler.writeOutputAndClearBuffer();

            if(game.gameOver()){
                String tryAgain = ioHandler.requestStringInput("Do you wanna try again?\n Yes/No");
                if(!(tryAgain=="2")){
                    game = level.getLevel(1);
                    ioHandler.writeOutputAndClearBuffer();
                }
                else{
                    break;
                }
            }

            String decision = ioHandler.requestStringInput("(Move/Turn)?");
            if(decision.equals("1")){
                int amount = ioHandler.requestIntegerInput("Move how many Steps?");
                if(amount>3|amount<0){
                    ioHandler.addToOutputBuffer("You can only make 1-3 Steps!");
                }
                else {
                    game.proceedWithMove(amount);
                }
            }else if(decision.equals("2")){
                String direction = ioHandler.requestStringInput("(Left/Right/Up/Down)?");
                if(!((direction.equals("1"))|(direction.equals("2"))|(direction.equals("3"))|(direction.equals("4")))){
                    ioHandler.addToOutputBuffer("Wrong Input!");
                }
                game.proceedWithTurn(direction);
            }
            else{
                ioHandler.addToOutputBuffer("Wrong Input!");
            }


            if(game.GameWon()){
                String nextLevel = ioHandler.requestStringInput("You have won the Level!! Wanna go to the next Level?\n");
                if(!(nextLevel=="2")){
                    i++;
                    game = level.getLevel(i);
                    if(game==null){
                        ioHandler.addToOutputBuffer("You completed the game!");
                        break;
                    }
                    else {
                        game.welcomeToNextLevel();
                    }
                }
                else{
                    break;
                }
            }
        }
    }
}
