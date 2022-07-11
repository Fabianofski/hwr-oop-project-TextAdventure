package hwr.oop;

import hwr.oop.gameobjects.fixed.*;
import hwr.oop.gameobjects.versatile.*;

import java.util.Random;

public class Game {
    private FixedObject[][] gameField;
    private int fieldSize;
    private Player player;
    private Ghost ghost;
    private IIOHandler ioHandler;
    Random random;
    public Ghost getGhost() {
        return ghost;
    }

    public Game(FixedObject[][] level, IIOHandler ioHandler, Player player, Ghost ghost) {
        this.fieldSize = level.length;
        this.ioHandler = ioHandler;
        this.gameField = level;
        this.player = player;
        this.ghost = ghost;
        this.random = new Random();
    }

    public Game(FixedObject[][] level, IIOHandler ioHandler, Player player, Ghost ghost, int seed) {
        this(level, ioHandler,player,ghost);
        this.random =  new Random(seed);
    }


        public void welcome(){
        ioHandler.addToOutputBuffer(
                "             /` './`\\\n" +
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
        ioHandler.addToOutputBuffer(
                "Instruction:\n'V'-is yourself. The point of the V, shows the direction you looking at" +
                "\n'O'-is everyone you can speak to." +
                "\n'G'-is the ghost that will follow you!" +
                "\n'H'-is the door to the next level!" +
                "\n'#'-is a wall, you can't there" +
                "\n'?'-is everything you can't see...It seems like your flashlight isn't strong enough.\n" +
                "\nYou can go 1-3 Steps. But if you run too fast you might get injured! So better watch out." +
                "\nIf you speak to somebody, you can choose between options. Simply just type '1' or '2'\n" +
                "You can test it straight away by saying if you wanna Move(1) or Turn(2)\n" +
                "Oh, and don't get caught by the ghost.\n" +
                "---------------------------------------------------\n");
        ioHandler.writeOutputAndClearBuffer();
        printGameFieldToOutput();
    }

    public void welcomeToNextLevel(){
        printGameFieldToOutput();
        ioHandler.addToOutputBuffer("\n------------------------------------------------\n");
        ioHandler.addToOutputBuffer("You opened the door...And a new room opened before your eyes.");
    }

    public void proceedWithTurn(int direction){
        player.turn(direction);
        printGameToOutput();
    }

    public void proceedWithMove(int moveAmount){
        boolean playerRanAgainstWall = willPlayerRunAgainstWall(moveAmount);

        printGameToOutput();

        checkIfPlayerWillTrip(moveAmount);
        if(playerRanAgainstWall)
            ioHandler.addToOutputBuffer("\n You can't run against the Wall! It hurts!" +
                "\nDamage taken: 1\nLife left:" + player.getLives());

        ghost.moveTowardsPosition(player.getPosition());
    }

    private boolean willPlayerRunAgainstWall(int moveAmount) {
        boolean playerRanAgainstWall = false;
        for (int i = 0; i < moveAmount; i++) {
            player.moveByAmount(1);
            Position newPlayerPos = player.getPosition();

            if(checkForWallOnPosition(newPlayerPos)){
                playerRanAgainstWall = true;
                player.moveByAmount(-1);
                player.harmPlayer(1);
                break;
            }
        }
        return playerRanAgainstWall;
    }

    private boolean checkForWallOnPosition(Position position) {
        FixedObject fixedObject = gameField[position.getX()][position.getY()];
        return fixedObject instanceof Wall;
    }

    private void checkIfPlayerWillTrip(int moveAmount) {
        if(moveAmount < 2) return;

        int randomNum = random.nextInt(6) ;
        if(randomNum==5){
            player.harmPlayer(1);
            ioHandler.addToOutputBuffer("\nDon't run so fast! You tripped over a broken plank!" +
                    " Watch out next time..\nDamage taken= -1\nLive: "+player.getLives()+"\n");
        }
    }

    private void printGameToOutput() {
        printGameFieldToOutput();
        Position playerPos = player.getPosition();
        FixedObject fixedObject = gameField[playerPos.x][playerPos.y];
        fixedObject.addEventToOutput();
    }

    private void printGameFieldToOutput(){
        StringBuilder GameState = new StringBuilder();

        for (int i = 0; i <= fieldSize; i++)
            GameState.append(i).append("  ");
        GameState.append("\n");

        for (int y = 0; y < fieldSize; y++) {
            GameState.append(y + 1).append("  ");
            for (int x = 0; x < fieldSize; x++) {
                renderObjectIcons(GameState, y, x);
            }
            GameState.append("\n");
        }

        ioHandler.addToOutputBuffer(GameState.toString());
        ioHandler.addToOutputBuffer("\n------------------------------------------------\n");
    }

    private void renderObjectIcons(StringBuilder GameState, int y, int x) {
        Position playerPos = player.getPosition();
        boolean playerSeesThisPosition = Math.abs(playerPos.getX() - x) <= 1 && Math.abs(playerPos.getY() - y) <= 1;

        if(playerSeesThisPosition){
            if (ghost.getPosition().equals(new Position(x, y)))
                GameState.append(ghost.getObjectIcon()).append("  ");
            else if (player.getPosition().equals(new Position(x, y)))
                GameState.append(player.getObjectIcon()).append("  ");
            else
                GameState.append(gameField[x][y].getObjectIcon()).append("  ");
        }
        else
            GameState.append("?"+"  ");
    }

    public boolean gameOver(){
        if(player.getLives()==0 || ghost.ghostIsAtPlayer()){
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

    public boolean levelIsCompleted(){
        return player.getHasOpenedDoor();
    }

}
