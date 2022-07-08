package hwr.oop;

import hwr.oop.gameobjects.fixed.*;
import hwr.oop.gameobjects.versatile.Ghost;
import hwr.oop.gameobjects.versatile.Player;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Levels {
    IIOHandler ioHandler;
    int length;
    Game game;
    Player player;
    Ghost ghost;
    String currentLevel;
    FixedObject[][] Level = new FixedObject[9][9];
    public Levels(){
        ioHandler = new IOHandler(System.in, System.out);
        length = 9;
    }


    private Game Level1(){
        this.game = null;
        this.player=null;
        this.ghost=null;
        Position playerPos = new Position(5, 5);
        Position ghostPos = new Position(6, 2);
        setup(playerPos,ghostPos);
        for (int x = 0; x < this.length; x++) {
            for (int y = 0; y < this.length; y++) {
                Level[x][y] = new Nothing(ioHandler);
            }
        }
        Level[5][6] = new Door(ioHandler,player);
        Level[5][7] = new NPC(ioHandler, player, NpcTypes.Mariel);
        Level[5][8] = new NPC(ioHandler, player, NpcTypes.Michelle);

        for (int i = 0; i < 6; i++) {
            Level[i + 2][4] = new Wall(ioHandler);
        }
        game = new Game(Level, ioHandler, player, ghost);
        game.gameBegin();
        currentLevel = "Level1";
        return game;

    }
    private void setup(Position playerPos, Position ghostPos){
        player = new Player(playerPos, this.length);
        player.setPosition(playerPos);
        ghost = new Ghost(ghostPos, player);
        ghost.setStartPosition(ghostPos);
        player.restart();

    }
    private boolean checkIfLevelExist(int i)
    {
        boolean a=true;
        try {
            Method[] methods = Levels.class.getDeclaredMethods();
            for (Method method : methods) {
                method.setAccessible(true);
                String MethodName = method.getName();
                if(MethodName.equals("Level"+i)){
                   return true;
                }
                else{
                    a = false;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }
    public Game getLevel(int i) {
        Game game1 = null;
        if (checkIfLevelExist(i)) {
            try {
                String Level = "hwr.oop.Levels";
                Class<?> c = Class.forName(Level);
                Method method = c.getDeclaredMethod("Level" + i);
                game1 = (Game) method.invoke(this);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

        }
        return game1;
    }
    private Game Level2(){
        Position playerPos = new Position(1, 1);
        Position ghostPos = new Position(0, 3);
        setup(playerPos,ghostPos);
        FixedObject[][] testLevel = new FixedObject[9][9];
        for (int x = 0; x < this.length; x++) {
            for (int y = 0; y < this.length; y++) {
                testLevel[x][y] = new Nothing(ioHandler);
            }
        }
        testLevel[5][7] = new Door(ioHandler,player);
        testLevel[8][4] = new NPC(ioHandler, player, NpcTypes.Milhouse);
        //testLevel[5][8] = new NPC(ioHandler, player, NpcTypes.Michelle);

        for (int i = 8; i < 0; i++) {
            testLevel[i - 2][4] = new Wall(ioHandler);
        }
        game = new Game(testLevel, ioHandler, player, ghost);
        currentLevel = "Level2";
        return game ;
    }
    private Game Level3(){
        Position playerPos = new Position(6, 8);
        Position ghostPos = new Position(8, 5);
        setup(playerPos,ghostPos);
        FixedObject[][] testLevel = new FixedObject[9][9];
        for (int x = 0; x < this.length; x++) {
            for (int y = 0; y < this.length; y++) {
                testLevel[x][y] = new Nothing(ioHandler);
            }
        }
        testLevel[8][2] = new Door(ioHandler,player);
        testLevel[0][8] = new NPC(ioHandler, player, NpcTypes.Mariel);
        testLevel[3][4] = new NPC(ioHandler, player, NpcTypes.Michelle);

        for (int i = 0; i < 5; i++) {
            testLevel[i + 3][5] = new Wall(ioHandler);
        }
        for (int i = 0; i < 6; i++) {
            testLevel[1][i+2] = new Wall(ioHandler);
        }
        for (int i = 8; i > 4; i--) {
            testLevel[5][i-5] = new Wall(ioHandler);
        }
        game = new Game(testLevel, ioHandler, player, ghost);
        currentLevel = "Level3";
        return game ;
    }
    public IIOHandler getIOHandler(){
        return ioHandler;
    }
    public String getCurrentLevel(){
        return currentLevel;
    }
}
