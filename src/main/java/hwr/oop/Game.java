package hwr.oop;

import hwr.oop.gameobjects.fixed.*;
import hwr.oop.gameobjects.versatile.*;

import java.util.Objects;
import java.util.Scanner;

public class Game {

    public FixedObject[][] getGameField() {
        return GameField;
    }
    static Scanner in;
    FixedObject[][] GameField;
    int fieldSize;
    Player player;
    Ghost ghost;

    public static void main(String[] args) {
        in = new Scanner(System.in);

        Game game = new Game(setUpTestLevel(), new Position(5,5), new Position(2,3));
        game.printGameStateToConsole();
        while (true){
            game.proceed();
        }
    }

    private static FixedObject[][] setUpTestLevel(){
        FixedObject[][] testLevel = new FixedObject[9][9];
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                testLevel[x][y] = new Nothing();
            }
        }
        testLevel[5][6] = new Door();
        return testLevel;
    }

    public Game(FixedObject[][] level, Position playerPos, Position ghostPos) {
        this.fieldSize = level.length;
        this.GameField = level;
        player = new Player(playerPos);
        ghost = new Ghost(ghostPos, player);
    }


    public void proceed(){
        System.out.println("(Move/Turn)?");
        String decision = in.next();

        if(decision.equals("Turn"))
            proceedWithRotation();
        else
            proceedWithMove();

        // CONSOLE
        printGameStateToConsole();
        Position playerPos = player.getPosition();
        GameField[playerPos.x][playerPos.y].triggerEvent();
    }

    private void proceedWithMove() {
        // MOVE
        System.out.println("Move how many Steps?");
        int amount = in.nextInt();
        player.moveByAmount(amount);
    }

    private void proceedWithRotation() {
        // ROTATE
        System.out.println("Turn (Right/Left/Stay)?");
        String direction = in.next();
        if(!direction.equals("Stay")) player.turn(direction.equals("Right"));
    }

    public String printGameStateToConsole(){
        StringBuilder GameState = new StringBuilder();
        GameState.append("0123456789\n");
        for (int y = 0; y < fieldSize; y++) {
            GameState.append(y + 1);
            for (int x = 0; x < fieldSize; x++) {
                if(Objects.equals(player.getPosition(), new Position(x, y)))
                    GameState.append(player.getObjectIcon());
                else if(Objects.equals(ghost.getPosition(), new Position(x, y)))
                    GameState.append(ghost.getObjectIcon());
                else
                    GameState.append(GameField[x][y].getObjectIcon());
            }
            GameState.append("\n");
        }
        System.out.println(GameState);
        return GameState.toString();
    }
}
