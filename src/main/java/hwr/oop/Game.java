package hwr.oop;

import hwr.oop.gameobjects.fixed.*;
import hwr.oop.gameobjects.versatile.*;

import java.util.Objects;

public class Game {
    FixedObject[][] GameField;
    int fieldSize;
    Player player;
    Ghost ghost;

    public Player getPlayer() {
        return player;
    }

    public Ghost getGhost() {
        return ghost;
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public Game(FixedObject[][] level, Position playerPos, Position ghostPos) {
        this.fieldSize = level.length;
        this.GameField = level;
        this.player = new Player(playerPos, this);
        this.ghost = new Ghost(ghostPos, player);
    }

    public void proceed(String direction){
        player.turn(direction.equals("Right"));
        printConsoleOutput();
    }

    public void proceed(int moveAmount){
        player.moveByAmount(moveAmount);
        printConsoleOutput();
    }

    private void printConsoleOutput() {
        printGameStateToConsole();
        Position playerPos = player.getPosition();
        GameField[playerPos.x][playerPos.y].triggerEvent();
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
