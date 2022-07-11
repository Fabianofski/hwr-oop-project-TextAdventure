package hwr.oop.gameobjects.fixed;

import hwr.oop.IIOHandler;
import hwr.oop.gameobjects.versatile.Player;

public class NPCMilhouse extends NPC{
    public NPCMilhouse(IIOHandler ioHandler, Player player) {
        super(ioHandler, player);
    }

    @Override
    public void talkToNPC() {
        String response = ioHandler.requestStringInput("            (.,------...__\n" +
                "         _.'\"             `.\n" +
                "       .'  .'   `, `. `.    `\n" +
                "      . .'   .'/''--...__`.  \\\n" +
                "     . .--.`.  ' \"-.     '.  |\n" +
                "     ''  .'  _.' .())  .--\":/\n" +
                "     ''(  \\_\\      '   (()(\n" +
                "     ''._'          (   \\ '\n" +
                "     ' `.            `--'  '\n" +
                "      `.:    .   `-.___.'  '\n" +
                "       `.     .    _  _  .'\n" +
                "         )       .____.-'\n" +
                "       .'`.        (--..\n" +
                "     .' \\  /\\      / /  `.\n" +
                "   .'    \\(  \\    /|/     `.\n" +
                " .'           \\__/          `.\n" +
                "/      |        o      |      \\\n" +
                "       |               |      |" +
                "\nMilhouse:\nWho are you? What do you want?!" +
                "\nYou:\n I am searching for a Key! (1) / Ugh never mind... (2)");
        if (response.equals("1")) {
            ioHandler.addToOutputBuffer("\nMilhouse:\nWhat? Wait lemme search...\n\nOh! I have got here something!");
            givePlayerKey();
            playerSpokeToNPC =true;
        } else ioHandler.addToOutputBuffer("\nWeirdo....");
    }
}
