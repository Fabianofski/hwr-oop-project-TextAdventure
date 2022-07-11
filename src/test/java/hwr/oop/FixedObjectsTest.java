package hwr.oop;

import hwr.oop.gameobjects.fixed.*;
import hwr.oop.gameobjects.versatile.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FixedObjectsTest {

    IIOHandler ioHandler;
    @BeforeEach
    void setUp() {
        ByteArrayInputStream input = new ByteArrayInputStream("TestResponse".getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        ioHandler = new IOHandler(input, new PrintStream(output));
    }

    @Nested
    class DoorTests{
        Door door;

        @BeforeEach
        void setUp() {
            door = new Door(ioHandler,new Player(new Position(2,3),9));
        }

        @Test
        void door_isFieldObject(){
            assertThat(door)
                    .isInstanceOf(FixedObject.class)
                    .isInstanceOf(Door.class);
        }

        @Test
        void door_getObjectIcon_iconIs0() {
            String icon = door.getObjectIcon();
            assertThat(icon).isEqualTo("H");
        }

        @Test
        void door_writeEventIOHandler_writesToIOHandler() {
            door.addEventToOutput();

            assertThat(ioHandler.getOutputBuffer()).isEqualTo("\nYou don't have a Key to open the door.");
        }
        @Test
        void door_Opened() {
            Player player = new Player(new Position(2,3),9);
            player.giveKey();
            door = new Door(ioHandler,player);
            door.addEventToOutput();

            assertThat(ioHandler.getOutputBuffer()).isEqualTo("You opened the door!");
        }

    }
    @Nested
    class NothingTests{
        Nothing nothing;

        @BeforeEach
        void setUp() {
            nothing = new Nothing(ioHandler);
        }

        @Test
        void nothing_isFieldObject(){
            nothing = new Nothing(ioHandler);
            assertThat(nothing)
                    .isInstanceOf(FixedObject.class)
                    .isInstanceOf(Nothing.class);
        }

        @Test
        void nothing_getObjectIcon_iconIsUnderscore() {
            String icon = nothing.getObjectIcon();
            assertThat(icon).isEqualTo("_");
        }

        @Test
        void nothing_writeEventIOHandler_writesToIOHandler() {
            nothing.addEventToOutput();

            assertThat(ioHandler.getOutputBuffer()).isEqualTo("");
        }
    }
    @Nested
    class NPCTests{
        NPC npc;
        Player player;

        @BeforeEach
        void setUp() {
            player = new Player(new Position(), 9);
            npc = new NPCMariel(ioHandler, player);
        }

        @Test
        void npc_isFieldObject(){
            npc = new NPCMariel(ioHandler, player);
            assertThat(npc)
                    .isInstanceOf(FixedObject.class)
                    .isInstanceOf(NPC.class);
        }

        @Test
        void npc_getObjectIcon_iconIsO() {
            String icon = npc.getObjectIcon();
            assertThat(icon).isEqualTo("O");
        }
        @Test
        void npc_stupidNPC_writeEventIOHandlerFirstResponse_writesToIOHandler() {
            npc = new NPC(ioHandler,player);
            npc.addEventToOutput();
            assertThat(ioHandler.getOutputBuffer()).isEqualTo("\nThis is a stupid NPC.");
        }
        @Test
        void npc_Mariel_writeEventIOHandlerFirstResponse_writesToIOHandler() {
            npc.addEventToOutput();
            assertThat(ioHandler.getOutputBuffer()).isEqualTo("\nYep.");
        }
        @Test
        void npc_Milhouse_writeEventIOHandlerFirstResponse_writesToIOHandler() {
            npc = new NPCMilhouse(ioHandler,player);
            npc.addEventToOutput();
            assertThat(ioHandler.getOutputBuffer()).isEqualTo("\nWeirdo....");
        }
        @Test
        void npc_Michelle_writeEventIOHandlerFirstResponse_writesToIOHandler() {
            npc = new NPCMichelle(ioHandler,player);
            npc.addEventToOutput();
            assertThat(ioHandler.getOutputBuffer()).isEqualTo("    .--..-\"\"\"\"-..--.\n" +
                    "   ///`/////////\\`\\\\\\\n" +
                    "   ||/ |///\"\"\\\\\\| \\||\n" +
                    "   ##  (  6. 6  )  ##\n" +
                    "   /_\\  \\  _.  /  /_\\\n" +
                    "        _`)  (`_\n" +
                    "      /`  '--'  `\\\n" +
                    "     /    _,,_    \\\n" +
                    "Michelle:\n" +
                    " Uhm Sorry who are you? Momma doesn't allow me to speak with strangers...");
        }

        @Test
        void npc_triggeredEventTwoTimes_writesToIoHandler(){
            ByteArrayInputStream input = new ByteArrayInputStream("1".getBytes());
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            ioHandler = new IOHandler(input, new PrintStream(output));
            npc = new NPCMariel(ioHandler, player);
            npc.addEventToOutput();

            ioHandler.clearOutputBuffer();
            npc.addEventToOutput();

            assertThat(ioHandler.getOutputBuffer()).isEqualTo("You already spoke to this NPC.");
        }
        @Test
        void npc_jonas_writeEventIOHandlerSecondResponse_writesToIOHandlerAndPlayerHasKey(){
            ByteArrayInputStream input = new ByteArrayInputStream("1".getBytes());
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            ioHandler = new IOHandler(input, new PrintStream(output));
            npc = new NPCMilhouse(ioHandler, player);

            npc.addEventToOutput();

            assertThat(ioHandler.getOutputBuffer()).isEqualTo("\n" +
                    "Milhouse:\n" +
                    "What? Wait lemme search...\n" +
                    "\n" +
                    "Oh! I have got here something!\n" +
                    "\n" +
                    "You received a Key!\n" +
                    "                   __\n" +
                    "                  /o \\_____\n" +
                    "                  \\__/-=\"=\"`");

            boolean playerHasKey = player.hasKey();
            assertThat(playerHasKey).isTrue();
        }
        @Test
        void npc_writeEventIOHandlerSecondResponse_writesToIOHandlerAndPlayerHasKey() {
            ByteArrayInputStream input = new ByteArrayInputStream("1".getBytes());
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            ioHandler = new IOHandler(input, new PrintStream(output));
            npc = new NPCMariel(ioHandler, player);

            npc.addEventToOutput();

            assertThat(ioHandler.getOutputBuffer()).isEqualTo(
                    "\n\nYou received a Key!\n" +
                    "                   __\n" +
                    "                  /o \\_____\n" +
                    "                  \\__/-=\"=\"`");

            boolean playerHasKey = player.hasKey();
            assertThat(playerHasKey).isTrue();
        }
    }
    @Nested
    class WallTests {

        Wall wall;

        @BeforeEach
        void setUp() {
            wall = new Wall(ioHandler);
        }

        @Test
        void wall_isFieldObject() {
            assertThat(wall)
                    .isInstanceOf(FixedObject.class)
                    .isInstanceOf(Wall.class);
        }

        @Test
        void wall_getObjectIcon_iconIsHashtag() {
            String icon = wall.getObjectIcon();
            assertThat(icon).isEqualTo("#");
        }

        @Test
        void wall_writeEventIOHandler_writesToIOHandler() {
            wall.addEventToOutput();

            assertThat(ioHandler.getOutputBuffer()).isEqualTo("\nYou shouldn't be stuck in the wall!");
        }
    }
}
