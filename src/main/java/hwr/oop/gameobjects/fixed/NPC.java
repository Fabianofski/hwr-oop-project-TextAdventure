package hwr.oop.gameobjects.fixed;

import hwr.oop.IIOHandler;
import hwr.oop.IOHandler;
import hwr.oop.gameobjects.versatile.Player;

public class NPC implements FixedObject {

    IIOHandler ioHandler;
    Player player;
    NpcTypes name;
    boolean playerSpoke;

    public NPC(IIOHandler ioHandler, Player player, NpcTypes name) {
        this.playerSpoke=false;
        this.ioHandler = ioHandler;
        this.player = player;
        this.name = name;
    }

    @Override
    public String getObjectIcon() {
        return "O";
    }

    @Override
    public void writeEventToIOHandler() {
        if (this.playerSpoke==false){
            checkName(name);
        }
        else{
            ioHandler.addToOutputBuffer("You already spoke to them.");
        }

    }
    //Hier ist viel Dialog, muss aber sein bei NPCs :D
    public void checkName(NpcTypes npctype){
        switch(npctype) {
            case Mariel: {
                String response = ioHandler.requestStringInput("                    _,,,_\n" +
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
                        "\nMariele:\n" +
                        "Hello Dear, I heard you are searching for something?\n" +
                        "\nYou:" +
                        "\nYes, can I have a Key? / No.");
                if (response.equals("1")) {
                    ioHandler.addToOutputBuffer("\nMariele:\nHere is your key!");
                    givenKeyEvent();
                    playerSpoke=true;
                } else ioHandler.addToOutputBuffer("\nYep.");
                break;
            }
            case Jonas:{
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
                        "\nJonas:\nWho are you? What do you want?!" +
                        "\nYou:\n I am searching for a Key!/ Ugh never mind...");
                if (response.equals("1")) {
                    ioHandler.addToOutputBuffer("\nJonas:\nWhat? Wait lemme search...\n\nOh! I have got here something!");
                    givenKeyEvent();
                    playerSpoke=true;
                } else ioHandler.addToOutputBuffer("\nWeirdo....");
                break;
            }
            case Michelle: {
                ioHandler.addToOutputBuffer("    .--..-\"\"\"\"-..--.\n" +
                        "   ///`/////////\\`\\\\\\\n" +
                        "   ||/ |///\"\"\\\\\\| \\||\n" +
                        "   ##  (  6. 6  )  ##\n" +
                        "   /_\\  \\  _.  /  /_\\\n" +
                        "        _`)  (`_\n" +
                        "      /`  '--'  `\\\n" +
                        "     /    _,,_    \\"+
                        "\nMichelle:\n Uhm Sorry who are you? Momma doesn't allow me to speak with strangers...");
            }
        }
    }

    public void givenKeyEvent(){
        player.giveKey();
        ioHandler.addToOutputBuffer("\n\nYou received a Key!\n" +
                "                   __\n" +
                "                  /o \\_____\n" +
                "                  \\__/-=\"=\"`");
    }
}
