package hwr.oop;

import hwr.oop.gameobjects.fixed.*;
import hwr.oop.gameobjects.versatile.*;

public class Game {

    public FieldObject[][] getGameField() {
        return GameField;
    }

    FieldObject[][] GameField;
    int fieldSize;
    Player player;
    Ghost ghost;

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
        return Math.random() < 0.9 ? new Nothing() : new Nothing();
    }

    public void proceed(){
        // TODO: implement proceed
    }

    public String printGameStateToConsole(){
        StringBuilder GameState = new StringBuilder();
        GameState.append("|==========|\n");
        for (int x = 0; x < fieldSize; x++) {
            GameState.append("|");
            for (int y = 0; y < fieldSize; y++) {
                GameState.append(getIconForFieldObject(GameField[x][y]));
            }
            GameState.append("|\n");
        }
        GameState.append("|==========|");
        System.out.println(GameState);
        return GameState.toString();
    }

    private String getIconForFieldObject(FieldObject object){
        if(object.getClass() == Nothing.class) return " ";
        else return "+";
        // TODO: Implement Icons for all FieldObjects
    }
}
