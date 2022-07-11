package hwr.oop.gameobjects.fixed;

import hwr.oop.IIOHandler;
import hwr.oop.gameobjects.versatile.Player;

public class NPCMichelle extends NPC{
    public NPCMichelle(IIOHandler ioHandler, Player player) {
        super(ioHandler, player);
    }

    @Override
    public void talkToNPC() {
        ioHandler.addToOutputBuffer(
                "    .--..-\"\"\"\"-..--.\n" +
                "   ///`/////////\\`\\\\\\\n" +
                "   ||/ |///\"\"\\\\\\| \\||\n" +
                "   ##  (  6. 6  )  ##\n" +
                "   /_\\  \\  _.  /  /_\\\n" +
                "        _`)  (`_\n" +
                "      /`  '--'  `\\\n" +
                "     /    _,,_    \\"+
                "\nMichelle:\n Uhm Sorry who are you? Momma doesn't allow me to speak with strangers...");
        playerSpokeToNPC = true;
    }
}
