package hwr.oop.gameobjects.fixed;

import hwr.oop.IIOHandler;
import hwr.oop.gameobjects.versatile.Player;

public class NPC implements FixedObject {

    IIOHandler ioHandler;
    Player player;

    public NPC(IIOHandler ioHandler, Player player) {
        this.ioHandler = ioHandler;
        this.player = player;
    }

    @Override
    public String getObjectIcon() {
        return "Ω";
    }

    @Override
    public void writeEventToIOHandler() {
        String response = ioHandler.requestStringInput("\nWhats popping?");
        if(response.equals("Give Key!")) {
            ioHandler.addToOutputBuffer("\nHere is your key!");
            player.giveKey();
        }
        else ioHandler.addToOutputBuffer("\nYep.");
    }
}
