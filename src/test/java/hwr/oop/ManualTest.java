package hwr.oop;

import hwr.oop.gameobjects.fixed.Door;
import hwr.oop.gameobjects.fixed.FixedObject;
import hwr.oop.gameobjects.fixed.Nothing;
import hwr.oop.gameobjects.fixed.Wall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class ManualTest {
    Game game;

    @BeforeEach
    void setUp() {
        FixedObject[][] testLevel = new FixedObject[9][9];
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                testLevel[x][y] = new Nothing();
            }
        }
        testLevel[5][6] = new Door();

        for (int i = 0; i < 6; i++) {
            testLevel[i + 2][4] = new Wall();
        }

        game = new Game(testLevel, new Position(5,5), new Position(2,3));
    }

    @Test
    @Disabled
    void main() {
        game.printGameStateToConsole();
        Scanner in = new Scanner(System.in);
        while (true){
            System.out.println("(Move/Turn)?");
            String decision = in.next();

            if(decision.equals("Move")){
                System.out.println("Move how many Steps?");
                int amount = in.nextInt();
                game.proceed(amount);
            }else{
                System.out.println("Turn (Right/Left)?");
                String direction = in.next();
                game.proceed(direction);
            }
        }
    }
}
