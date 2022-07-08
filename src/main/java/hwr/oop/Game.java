package hwr.oop;

import hwr.oop.gameobjects.fixed.*;
import hwr.oop.gameobjects.versatile.*;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Game {
    private FixedObject[][] gameField;
    private int fieldSize;
    private Player player;
    private Ghost ghost;
    private IIOHandler ioHandler;

    public Ghost getGhost() {
        return ghost;
    }

    public Game(FixedObject[][] level, IIOHandler ioHandler, Player player, Ghost ghost) {
        this.fieldSize = level.length;
        this.ioHandler = ioHandler;
        this.gameField = level;
        this.player = player;
        this.ghost = ghost;
    }

    public void nextLevel(){
        writeGameStateToIOHandler();
        ioHandler.addToOutputBuffer("\n------------------------------------------------\n");
        ioHandler.addToOutputBuffer("You opened the door...And a new room opened before your eyes.");
    }
    public void gameBegin(){
        ioHandler.addToOutputBuffer("             /` './`\\\n" +
                "            ;     \\  \\  ___\n" +
                "            |      ' .'`    `.\n" +
                "           .        /  O  o   |\n" +
                "       ___ |_       | _|`'/   ;\n" +
                "   .-''   `| `'----/ o\"..-`o / \n" +
                "  (        `._   .'    o'_.-' \n" +
                "   `-.._____..-'___..--'`\\\\> \n" +
                "      |/  .-.\\| |  O||  O|`  \n" +
                "       |/|.-,\\/ '._.' _-' \\  \n" +
                "        ' \\_, _/     '  ) ;   \n" +
                "      ._.-. _\\ \\    `'-' /    \n" +
                " .--.  `-,_(_); `-..__.-' "+
                "\nG'day' Adventurer!\nI've heard you were commin' to me." +
                " You are willing to explore the abandoned House right?\n" +
                "Hahaha.. I've heard it's not that much abandoned anymore, but still, we don't know anything 'bout it.\n" +
                "Yeah..Anyways, here is your flashlight, watch out. And if you see anybody, you might speak to them." +
                "Maybe you can tell me later if anybody lives there..." +
                "\n Oh! And here is the key to the first door!\n" +
                "Sadly, I only have this one... To open other doors you might have to search for a key..Sorry mate");
        ioHandler.addToOutputBuffer("\n------------------------------------------------\n");
        ioHandler.addToOutputBuffer("Instruction:\n'V'-is yourself. The point of the V, shows the direction you looking at" +
                "\n'O'-is everyone you can speak to." +
                "\n'G'-is the ghost that will follow you!" +
                "\n'H'-is the door to the next level!" +
                "\n'#'-is a wall, you can't there" +
                "\n'?'-is everything you can't see...It seems like your flashlight isn't strong enough.\n" +
                "\nYou can go 1-3 Steps. But if you run too fast you might ger injured! So better watch out." +
                "\nIf you speak to somebody, you can choose between options. Simply just type '1' or '2'\n" +
                "You can test it straight away by saying if you wanna Move(1) or Turn(2)\n" +
                "Oh, and don't get caught by the ghost.\n" +
                "---------------------------------------------------\n");
        ioHandler.writeOutputAndClearBuffer();
        writeGameStateToIOHandler();
    }
    public void proceed(String direction){
        player.turn(direction);
        writeToIOHandler();
    }

    public void proceed(int moveAmount){
        boolean a = false;
        for (int i = 0; i < moveAmount; i++) {
            Position last = player.getPosition();
            int x = last.getX();
            int y = last.getY();
            player.moveByAmount(1);
            a = checkIfWall(x, y);
            if(a){
                break;
            }
        }
        ghost.moveTowardsPosition(player.getPosition());
        writeToIOHandlerCheckWall(a);
        tripping(moveAmount);
    }

    private void tripping(int moveAmount) {
        if(moveAmount >1){
            int randomNum = ThreadLocalRandom.current().nextInt(1, 6);
            if(randomNum==5){
                player.harmPlayer(1);
                ioHandler.addToOutputBuffer("\nDon't run so fast! You tripped over a broken plank!" +
                        " Watch out next time..\nDamage taken= -1\nLive: "+player.getLives()+"\n");
            }
        }
    }

    private boolean checkIfWall(int x, int y) {
        Position playerPos = player.getPosition();
        FixedObject fixedObject = gameField[playerPos.x][playerPos.y];
        if(fixedObject.getObjectIcon().equals("#")){
            System.out.println(player.getPosition().toString());
            player.setPosition(new Position(x, y));
            System.out.println(player.getPosition().toString());
            return true;
        }
        else{return false;}
    }

    public boolean gameOver(){
        if(player.getLives()==0|ghost.ghostIsAtPlayer()){
            ioHandler.addToOutputBuffer("     .-.\n" +
                    "   .'   `.\n" +
                    "   :g g   :\n" +
                    "   : o    `.\n" +
                    "  :         ``.\n" +
                    " :             `.\n" +
                    ":  :         .   `.\n" +
                    ":   :          ` . `.\n" +
                    " `.. :            `. ``;\n" +
                    "    `:;             `:'\n" +
                    "       :              `.\n" +
                    "        `.              `.     .\n" +
                    "          `'`'`'`---..,___`;.-'");
            ioHandler.addToOutputBuffer("\nSorry, you died!\n");
            return true;
        }
        else{
            return false;
        }
    }
    public boolean GameWon(){
        return player.getHasOpenedDoor();
    }

    private void writeToIOHandlerCheckWall(boolean i) {
        writeToIOHandler();
        if(i){
            player.harmPlayer(1);
            ioHandler.addToOutputBuffer("\n You can't run against the Wall! It hurts!" +
                    "\nDamage taken: 1\nLife left:"+player.getLives());
        }
    }

    private void writeToIOHandler() {
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
                int xp = player.getPosition().getX();
                int yp = player.getPosition().getY();
                boolean whatPlayerSees=((xp+1==x&yp==y)|(xp+1==x&yp-1==y)|(xp+1==x&yp+1==y)|
                        (xp==x&yp+1==y)|(xp==x&yp==y)|(xp==x&yp-1==y)|(yp-1==y&xp-1==x)|
                        (xp-1==x&yp==y)|(xp-1==x&yp+1==y));
                if(whatPlayerSees){
                    if (Objects.equals(ghost.getPosition(), new Position(x, y)))
                        GameState.append(ghost.getObjectIcon()+"  ");
                    else if (Objects.equals(player.getPosition(), new Position(x, y)))
                        GameState.append(player.getObjectIcon()+"  ");
                    else
                        GameState.append(gameField[x][y].getObjectIcon()+"  ");
                }
                else{
                    GameState.append("?"+"  ");
                }
            }
            GameState.append("\n");
        }
        ioHandler.addToOutputBuffer(GameState.toString());
        ioHandler.addToOutputBuffer("\n------------------------------------------------\n");
    }

}
