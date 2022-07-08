package hwr.oop;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

public class ManualTest {
    Game game;
    IIOHandler ioHandler;
    Levels level;

    @Test
    @Disabled
    void manualTestLevel1() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {

        level = new Levels();
        game = level.getLevel(1);
        ioHandler = level.getIOHandler();

        while (true){
            int i = 1;
            ioHandler.writeOutputAndClearBuffer();

            if(game.gameOver()){
                ioHandler.addToOutputBuffer("\nSorry, you died!\n");
                String tryAgain = ioHandler.requestStringInput("Do you wanna try again?\n Yes/No");
                if(!(tryAgain=="2")){
                    game.restart();
                    game = level.getLevel(1);
                    ioHandler.clearOutputBuffer();
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
                    game.proceed(amount);
                }
            }else{
                String direction = ioHandler.requestStringInput("(Left/Right/Up/Down)?");
                game.proceed(direction);
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
                            game.nextLevel();
                        }
                    }
                    else{
                        break;
                    }
            }
        }

    }
}
