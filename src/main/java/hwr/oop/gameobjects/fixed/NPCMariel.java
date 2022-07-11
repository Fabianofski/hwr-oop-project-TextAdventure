package hwr.oop.gameobjects.fixed;

import hwr.oop.IIOHandler;
import hwr.oop.gameobjects.versatile.Player;

public class NPCMariel extends NPC{
    public NPCMariel(IIOHandler ioHandler, Player player) {
        super(ioHandler, player);
    }

    @Override
    public void talkToNPC() {
        String response = ioHandler.requestStringInput(
                "                    _,,,_\n" +
                        "                  .'     `'.\n" +
                        "                 /     ____ \\\n" +
                        "                |    .'_  _\\/\n" +
                        "                /    ) a  a|\n" +
                        "               /    (    > |\n" +
                        "              (      ) ._  /\n" +
                        "              )    _/-.__.'`\\\n" +
                        "             (  .-'`-.   \\__ )\n" +
                        "              `/      `-./  `.\n" +
                        "               |    \\      \\  \\\n" +
                        "               |     \\   \\  \\  \\\n" +
                        "               |\\     `. /  /   \\"+
                        "\nMariel:\n" +
                        "Hello Dear, I heard you are searching for something?\n" +
                        "\nYou:" +
                        "\nYes, can I have a Key? (1) / No. (2)");
        if (response.equals("1")) {
            ioHandler.addToOutputBuffer("\nMariel:\nHere is your key!");
            ioHandler.writeOutputAndClearBuffer();
            givePlayerKey();
            playerSpokeToNPC =true;
        } else ioHandler.addToOutputBuffer("\nYep.");
    }
}
