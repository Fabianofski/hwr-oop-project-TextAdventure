package hwr.oop;

import hwr.oop.gameobjects.fixed.Door;
import hwr.oop.gameobjects.fixed.FixedObject;
import hwr.oop.gameobjects.fixed.Nothing;
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

        game = new Game(testLevel, new Position(5,5), new Position(2,3));
    }

    @Test
    @Disabled
    void main(String[] args) {
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
