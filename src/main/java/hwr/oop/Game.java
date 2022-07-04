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
    private boolean runinwall;
    public Player getPlayer() {
        return player;
    }

    public Ghost getGhost() {
        return ghost;
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public Game(FixedObject[][] level, IIOHandler ioHandler, Player player, Ghost ghost) {
        this.fieldSize = level.length;
        this.ioHandler = ioHandler;
        this.gameField = level;
        this.player = player;
        this.ghost = ghost;

        writeGameStateToIOHandler();
    }

    public void proceed(String direction){
        boolean turnRight = direction.equals("Right");
        player.turn(turnRight);
        writeToIOHandler();
    }

    public void proceed(int moveAmount){
        Position last = player.getPosition();
        int x = last.getX();
        int y = last.getY();
        player.moveByAmount(moveAmount);
        checkIfWall(x, y);
        ghost.moveTowardsPosition(player.getPosition());
        writeToIOHandler();

    }

    private void checkIfWall(int x, int y) {
        Position playerPos = player.getPosition();
        FixedObject fixedObject = gameField[playerPos.x][playerPos.y];
        if(fixedObject.getObjectIcon()=="#"){
            player.setPosition(new Position(x, y));
            runinwall=true;
        }
    }

    public boolean gameOver(){
        if(player.getLives()==0|ghost.ghostIsAtPlayer()){
            return true;
        }
        else{
            return false;
        }

    }

    private void writeToIOHandler() {
        writeGameStateToIOHandler();
        Position playerPos = player.getPosition();
        FixedObject fixedObject = gameField[playerPos.x][playerPos.y];
        fixedObject.writeEventToIOHandler();
        if(runinwall){
            player.harmPlayer(1);
            ioHandler.addToOutputBuffer("\n You can't run against the Wall! It hurts!" +
             "\nDamage taken: 1\nLife left:"+player.getLives());
        }

    }

    private void writeGameStateToIOHandler(){
        StringBuilder GameState = new StringBuilder();
        GameState.append("0  1  2  3  4  5  6  7  8  9\n");
        for (int y = 0; y < fieldSize; y++) {
            GameState.append(y + 1+"  ");
            for (int x = 0; x < fieldSize; x++) {
                if (Objects.equals(ghost.getPosition(), new Position(x, y)))
                    GameState.append(ghost.getObjectIcon()+"  ");
                else if (Objects.equals(player.getPosition(), new Position(x, y)))
                    GameState.append(player.getObjectIcon()+"  ");
                else
                    GameState.append(gameField[x][y].getObjectIcon()+"  ");
            }
            GameState.append("\n");
        }
        ioHandler.addToOutputBuffer(GameState.toString());
    }

}
