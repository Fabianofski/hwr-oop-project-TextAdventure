package hwr.oop;

import hwr.oop.gameobjects.fixed.*;
import hwr.oop.gameobjects.versatile.Ghost;
import hwr.oop.gameobjects.versatile.Player;

public class Levels {
    IIOHandler ioHandler;
    int length;
    Game game;
    public Levels(){
        ioHandler = new IOHandler(System.in, System.out);
        length = 9;
    }


    public void Level1(){
        Position playerPos = new Position(5, 5);
        Player player = new Player(playerPos, this.length);
        Position ghostPos = new Position(2, 3);
        Ghost ghost = new Ghost(ghostPos, player);

        FixedObject[][] testLevel = new FixedObject[9][9];
        for (int x = 0; x < this.length; x++) {
            for (int y = 0; y < this.length; y++) {
                testLevel[x][y] = new Nothing(ioHandler);
            }
        }
        testLevel[5][6] = new Door(ioHandler);
        testLevel[5][7] = new NPC(ioHandler, player, NpcTypes.Jonas);
        testLevel[5][8] = new NPC(ioHandler, player, NpcTypes.Jonas);

        for (int i = 0; i < 6; i++) {
            testLevel[i + 2][4] = new Wall(ioHandler);
        }

        game = new Game(testLevel, ioHandler, player, ghost);
    }


    public Game getGame(){
        return game;
    }
    public IIOHandler getIOHandler(){
        return ioHandler;
    }
}
