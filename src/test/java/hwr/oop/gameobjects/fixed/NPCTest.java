package hwr.oop.gameobjects.fixed;

import hwr.oop.IIOHandler;
import hwr.oop.IOHandler;
import hwr.oop.Position;
import hwr.oop.gameobjects.versatile.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class NPCTest {

    IIOHandler ioHandler;
    NPC npc;
    Player player;

    @BeforeEach
    void setUp() {
        ByteArrayInputStream input = new ByteArrayInputStream("1".getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        ioHandler = new IOHandler(input, new PrintStream(output));
        player = new Player(new Position(), 9);
        npc = new NPC(ioHandler, player);
    }

    @Test
    void npc_isFieldObject() {
        assertThat(npc)
                .isInstanceOf(FixedObject.class)
                .isInstanceOf(NPC.class);
    }

    @Test
    void getObjectIcon_iconIsLetterO() {
        String icon = npc.getObjectIcon();
        assertThat(icon).isEqualTo("O");
    }

    @Test
    void addEventToOutput_generalNPCIsTalkedTo_AnswersFirstResponse() {
        npc.addEventToOutput();
        assertThat(ioHandler.getOutputBuffer()).isEqualTo("\nThis is a stupid NPC.");
    }

    @Test
    void addEventToOutput_MilhouseIsTalkedTo_AnswersFirstResponseAndGivesKey() {
        npc = new NPCMilhouse(ioHandler, player);

        npc.addEventToOutput();

        String expected =
                "\n" +
                        "Milhouse:\n" +
                        "What? Wait lemme search...\n" +
                        "\n" +
                        "Oh! I have got here something!\n" +
                        "\n" +
                        "You received a Key!\n" +
                        "                   __\n" +
                        "                  /o \\_____\n" +
                        "                  \\__/-=\"=\"`";
        assertThat(ioHandler.getOutputBuffer()).isEqualTo(expected);

        boolean playerHasKey = player.hasKey();
        assertThat(playerHasKey).isTrue();
    }

    @Test
    void addEventToOutput_MarielIsTalkedTo_AnswersFirstResponseAndGivesKey() {
        npc = new NPCMariel(ioHandler, player);

        npc.addEventToOutput();

        String expected =
                "\n\nYou received a Key!\n" +
                        "                   __\n" +
                        "                  /o \\_____\n" +
                        "                  \\__/-=\"=\"`";
        assertThat(ioHandler.getOutputBuffer()).isEqualTo(expected);

        boolean playerHasKey = player.hasKey();
        assertThat(playerHasKey).isTrue();
    }

    @Test
    void addEventToOutput_MichelleIsTalkedTo_AnswersFirstResponse() {
        npc = new NPCMichelle(ioHandler, player);
        npc.addEventToOutput();

        String expected =
                "    .--..-\"\"\"\"-..--.\n" +
                "   ///`/////////\\`\\\\\\\n" +
                "   ||/ |///\"\"\\\\\\| \\||\n" +
                "   ##  (  6. 6  )  ##\n" +
                "   /_\\  \\  _.  /  /_\\\n" +
                "        _`)  (`_\n" +
                "      /`  '--'  `\\\n" +
                "     /    _,,_    \\\n" +
                "Michelle:\n" +
                " Uhm Sorry who are you? Momma doesn't allow me to speak with strangers...";
        assertThat(ioHandler.getOutputBuffer()).isEqualTo(expected);
    }

    @Test
    void addEventToOutput_generalNPCIsTalkedToTwice_printsAlreadyTalkedToNPCMessage() {
        npc.addEventToOutput();
        ioHandler.clearOutputBuffer();
        npc.addEventToOutput();

        assertThat(ioHandler.getOutputBuffer()).isEqualTo("You already spoke to this NPC.");
    }

    @Test
    void addEventToOutput_MarielIsTalkedTo_AnswersSecondResponse() {
        ByteArrayInputStream input = new ByteArrayInputStream("2".getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        ioHandler = new IOHandler(input, new PrintStream(output));

        npc = new NPCMariel(ioHandler, player);
        npc.addEventToOutput();
        assertThat(ioHandler.getOutputBuffer()).isEqualTo("\nYep.");
    }

    @Test
    void addEventToOutput_MilhouseIsTalkedTo_AnswersSecondResponse() {
        ByteArrayInputStream input = new ByteArrayInputStream("2".getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        ioHandler = new IOHandler(input, new PrintStream(output));

        npc = new NPCMilhouse(ioHandler, player);
        npc.addEventToOutput();
        assertThat(ioHandler.getOutputBuffer()).isEqualTo("\nWeirdo....");
    }
}
