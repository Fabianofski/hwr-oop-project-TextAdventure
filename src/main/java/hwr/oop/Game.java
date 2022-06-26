package hwr.oop;

import hwr.oop.gameobjects.fixed.*;
import hwr.oop.gameobjects.versatile.*;

import java.util.Objects;
import java.util.Scanner;

public class Game {

    public FieldObject[][] getGameField() {
        return GameField;
    }
    static Scanner in;
    FieldObject[][] GameField;
    int fieldSize;
    Player player;
    Ghost ghost;

    public static void main(String[] args) {
        in = new Scanner(System.in);

        Game game = new Game(10);
        game.printGameStateToConsole();
        game.proceed();
        game.printGameStateToConsole();
    }

    public Game(int fieldSize) {
        this.fieldSize = fieldSize;
        this.GameField = new FieldObject[fieldSize][fieldSize];
        setUpGameField();
    }

    private void setUpGameField(){
        for (int x = 0; x < fieldSize; x++) {
            for (int y = 0; y < fieldSize; y++) {
                GameField[x][y] = generateRandomFieldObject();
            }
        }
        // TODO: Replace with random values
        player = new Player(new Position(5,5));
        ghost = new Ghost(new Position(2,3), player);
    }

    private FieldObject generateRandomFieldObject(){
        // TODO: Generate random Objects
        return new Nothing();
    }

    public void proceed(){
        System.out.print("Move how many Steps?");
        int x = in.nextInt();
        player.moveByAmount(new Position(x, 0));
    }

    public String printGameStateToConsole(){
        StringBuilder GameState = new StringBuilder();
        GameState.append("0123456789\n");
        for (int x = 0; x < fieldSize; x++) {
            GameState.append(x + 1);
            for (int y = 0; y < fieldSize; y++) {
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
