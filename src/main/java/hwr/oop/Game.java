package hwr.oop;

import hwr.oop.gameobjects.fixed.*;
import hwr.oop.gameobjects.versatile.*;

import java.util.Objects;

public class Game {
    private FixedObject[][] gameField;
    private int fieldSize;
    private Player player;
    private Ghost ghost;
    private IIOHandler ioHandler;

    public Player getPlayer() {
        return player;
    }

    public Ghost getGhost() {
        return ghost;
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public Game(FixedObject[][] level, IIOHandler ioHandler, Position playerPos, Position ghostPos) {
        this.fieldSize = level.length;
        this.ioHandler = ioHandler;
        this.gameField = level;
        this.player = new Player(playerPos, this);
        this.ghost = new Ghost(ghostPos, player);

        writeGameStateToIOHandler();
    }

    public void proceed(String direction){
        player.turn(direction.equals("Right"));
        writeIOHandler();
    }

    public void proceed(int moveAmount){
        player.moveByAmount(moveAmount);
        player.getPosition();
        ghost.moveTowardsPosition(player.getPosition());
        writeIOHandler();
    }
    public boolean gameOver(){
        return ghost.ghostIsAtPlayer();
    }

    private void writeIOHandler() {
        writeGameStateToIOHandler();
        Position playerPos = player.getPosition();
        FixedObject fixedObject = gameField[playerPos.x][playerPos.y];
        fixedObject.writeEventToIOHandler();
    }

    private void writeGameStateToIOHandler(){
        StringBuilder GameState = new StringBuilder();
        GameState.append("0  1  2  3  4  5  6  7  8  9\n");
        for (int y = 0; y < fieldSize; y++) {
            GameState.append(y + 1+"  ");
            for (int x = 0; x < fieldSize; x++) {
                if (Objects.equals(player.getPosition(), new Position(x, y)))
                    GameState.append(player.getObjectIcon()+"  ");
                else if (Objects.equals(ghost.getPosition(), new Position(x, y)))
                    GameState.append(ghost.getObjectIcon()+"  ");
                else
                    GameState.append(gameField[x][y].getObjectIcon()+"  ");
            }
            GameState.append("\n");
        }
        ioHandler.addToOutputBuffer(GameState.toString());
    }
}
