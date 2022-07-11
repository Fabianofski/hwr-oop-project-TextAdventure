package hwr.oop.gameobjects.fixed;

import hwr.oop.IIOHandler;
import hwr.oop.gameobjects.versatile.Player;

public class Door implements FixedObject {
    IIOHandler ioHandler;
    Player player;

    public Door(IIOHandler ioHandler, Player player) {
        this.ioHandler = ioHandler; this.player=player;
    }
    @Override
    public String getObjectIcon() {
        return "H";
    }

    @Override
    public void addEventToOutput() {
        if(player.hasKey()){
            ioHandler.addToOutputBuffer("You opened the door!");
            player.setHasOpenedDoor(true);
        }
        else{ioHandler.addToOutputBuffer("\nYou don't have a Key to open the door.");}

    }
}
