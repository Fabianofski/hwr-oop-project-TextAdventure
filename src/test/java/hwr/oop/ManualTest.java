package hwr.oop;

import hwr.oop.gameobjects.fixed.*;
import hwr.oop.gameobjects.versatile.Ghost;
import hwr.oop.gameobjects.versatile.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class ManualTest {
    Game game;
    IIOHandler ioHandler;
    Levels Level;

    @BeforeEach
    void setUp() {
      Level = new Levels();
      Level.Level1();
      game = Level.getGame();
      ioHandler = Level.getIOHandler();
    }

    @Test
    @Disabled
    void manualTestLevel1() {
        while (true){
            ioHandler.writeOutputAndClearBuffer();
            if(game.gameOver()){
                ioHandler.addToOutputBuffer("The ghost killed you!\n");
                String tryAgain = ioHandler.requestStringInput("Do you wanna try again?\n Yes/No");
                if(tryAgain =="yes"|tryAgain == "Yes"){
                    //nochmal starten
                }
                    break;//?
            }
            String decision = ioHandler.requestStringInput("(Move/Turn)?");

            if(decision.equals("Move")){
                int amount = ioHandler.requestIntegerInput("Move how many Steps?");
                if(amount>3){
                    ioHandler.addToOutputBuffer("You can't make that many Steps!");
                }
                else {
                    game.proceed(amount);
                }
            }else{
                String direction = ioHandler.requestStringInput("(Right/Left)?");
                game.proceed(direction);
            }
        }
    }
}
