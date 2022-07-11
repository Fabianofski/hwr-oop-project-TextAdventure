package hwr.oop.gameobjects.fixed;

import hwr.oop.IIOHandler;
import hwr.oop.gameobjects.versatile.Player;

public class NPC implements FixedObject {

    IIOHandler ioHandler;
    Player player;
    boolean playerSpokeToNPC;

    public NPC(IIOHandler ioHandler, Player player) {
        this.playerSpokeToNPC =false;
        this.ioHandler = ioHandler;
        this.player = player;
    }

    @Override
    public String getObjectIcon() {
        return "O";
    }

    @Override
    public void addEventToOutput() {
        if (playerSpokeToNPC)
            ioHandler.addToOutputBuffer("You already spoke to this NPC.");
        else
            talkToNPC();
    }

    public void talkToNPC(){
        ioHandler.addToOutputBuffer("\nThis is a stupid NPC.");
        playerSpokeToNPC = true;
    }

    public void givePlayerKey(){
        player.giveKey();
        ioHandler.addToOutputBuffer("\n\nYou received a Key!\n" +
                "                   __\n" +
                "                  /o \\_____\n" +
                "                  \\__/-=\"=\"`");
    }
}
