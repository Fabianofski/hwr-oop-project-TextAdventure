package hwr.oop;

import hwr.oop.gameobjects.fixed.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class ManualTest {
    Game game;
    IIOHandler ioHandler;

    @BeforeEach
    void setUp() {
        ioHandler = new IOHandler(System.in, System.out);

        FixedObject[][] testLevel = new FixedObject[9][9];
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                testLevel[x][y] = new Nothing(ioHandler);
            }
        }
        testLevel[5][6] = new Door(ioHandler);
        testLevel[5][7] = new NPC(ioHandler);

        for (int i = 0; i < 6; i++) {
            testLevel[i + 2][4] = new Wall(ioHandler);
        }

        game = new Game(testLevel, ioHandler, new Position(5,5), new Position(2,3));
    }

    @Test
    @Disabled
    void manualTest() {
        while (true){
            ioHandler.writeOutputAndClearBuffer();
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
