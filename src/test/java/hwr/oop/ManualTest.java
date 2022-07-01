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

    @BeforeEach
    void setUp() {
        ioHandler = new IOHandler(System.in, System.out);
        int length = 9;

        Position playerPos = new Position(5, 5);
        Player player = new Player(playerPos, length);
        Position ghostPos = new Position(2, 3);
        Ghost ghost = new Ghost(ghostPos, player);

        FixedObject[][] testLevel = new FixedObject[9][9];
        for (int x = 0; x < length; x++) {
            for (int y = 0; y < length; y++) {
                testLevel[x][y] = new Nothing(ioHandler);
            }
        }
        testLevel[5][6] = new Door(ioHandler);
        testLevel[5][7] = new NPC(ioHandler, player);

        for (int i = 0; i < 6; i++) {
            testLevel[i + 2][4] = new Wall(ioHandler);
        }

        game = new Game(testLevel, ioHandler, player, ghost);
    }

    @Test
    @Disabled
    void manualTest() {
        while (true){
            ioHandler.writeOutputAndClearBuffer();
            if(game.gameOver()){
                ioHandler.addToOutputBuffer("The ghost killed you!\n");
                String tryAgain = ioHandler.requestStringInput("Do you wanna try again?\n Yes/No");
                if(tryAgain=="yes"|tryAgain== "Yes"){
                    //nochmal starten lol
                }
                break;//?
            }
            String decision = ioHandler.requestStringInput("(Move/Turn)?");

            if(decision.equals("Move")){
                int amount = ioHandler.requestIntegerInput("Move how many Steps?");
                game.proceed(amount);
            }else{
                String direction = ioHandler.requestStringInput("(Right/Left)?");
                game.proceed(direction);
            }
        }
    }
}
