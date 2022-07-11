package hwr.oop;

import hwr.oop.gameobjects.fixed.*;
import hwr.oop.gameobjects.versatile.Ghost;
import hwr.oop.gameobjects.versatile.Player;

import java.util.ArrayList;
import java.util.List;

public class LevelManager {
    IIOHandler ioHandler;
    int length;
    Player player;
    Ghost ghost;
    int currentLevel;
    List<Game> levels;

    public LevelManager(IIOHandler ioHandler){
        this.ioHandler = ioHandler;
        this.length = 9;

        levels = new ArrayList<>();
        levels.add(Level1());
        levels.add(Level2());
        levels.add(Level3());
    }

    public Game getLevel(int i) {
        currentLevel = i;
        if(levels.size() > i) return levels.get(i);
        else return null;
    }

    private Game Level1(){
        Position playerPos = new Position(5, 5);
        Position ghostPos = new Position(6, 2);
        FixedObject[][] level = fillLevelWithBasicObjects(playerPos, ghostPos);

        level[5][6] = new Door(ioHandler,player);
        level[5][7] = new NPCMariel(ioHandler, player);
        level[5][8] = new NPCMichelle(ioHandler, player);

        for (int i = 0; i < 6; i++) {
            level[i + 2][4] = new Wall(ioHandler);
        }

        return new Game(level, ioHandler, player, ghost);
    }

    private Game Level2(){
        Position playerPos = new Position(1, 1);
        Position ghostPos = new Position(0, 3);
        FixedObject[][] level = fillLevelWithBasicObjects(playerPos, ghostPos);

        level[5][7] = new Door(ioHandler,player);
        level[8][4] = new NPCMilhouse(ioHandler, player);

        return new Game(level, ioHandler, player, ghost);
    }

    private Game Level3(){
        Position playerPos = new Position(6, 8);
        Position ghostPos = new Position(8, 5);
        FixedObject[][] level = fillLevelWithBasicObjects(playerPos, ghostPos);

        level[8][2] = new Door(ioHandler,player);
        level[0][8] = new NPCMariel(ioHandler, player);
        level[3][4] = new NPCMichelle(ioHandler, player);

        for (int i = 0; i < 5; i++) {
            level[i + 3][5] = new Wall(ioHandler);
        }
        for (int i = 0; i < 6; i++) {
            level[1][i+2] = new Wall(ioHandler);
        }
        for (int i = 8; i > 4; i--) {
            level[5][i-5] = new Wall(ioHandler);
        }

        return new Game(level, ioHandler, player, ghost);
    }

    private FixedObject[][] fillLevelWithBasicObjects(Position playerPos, Position ghostPos) {
        setup(playerPos,ghostPos);
        FixedObject[][] level = new FixedObject[9][9];
        for (int x = 0; x < this.length; x++) {
            for (int y = 0; y < this.length; y++) {
                level[x][y] = new Nothing(ioHandler);
            }
        }
        return level;
    }

    private void setup(Position playerPos, Position ghostPos){
        player = new Player(playerPos, this.length);
        player.setPosition(playerPos);
        ghost = new Ghost(ghostPos, player);
        ghost.setStartPosition(ghostPos);
        player.restart();

    }

    public int getCurrentLevel(){
        return currentLevel;
    }
}
