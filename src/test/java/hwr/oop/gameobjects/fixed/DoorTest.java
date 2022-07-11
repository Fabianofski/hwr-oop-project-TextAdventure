package hwr.oop.gameobjects.fixed;

import hwr.oop.IIOHandler;
import hwr.oop.IOHandler;
import hwr.oop.Position;
import hwr.oop.gameobjects.versatile.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DoorTest {

    IIOHandler ioHandler;
    Door door;

    @BeforeEach
    void setUp() {
        ByteArrayInputStream input = new ByteArrayInputStream("TestResponse".getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        ioHandler = new IOHandler(input, new PrintStream(output));
        door = new Door(ioHandler, new Player(new Position(2, 3), 9));
    }

    @Test
    void door_isFieldObject() {
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
        Player player = new Player(new Position(2, 3), 9);
        player.giveKey();
        door = new Door(ioHandler, player);
        door.addEventToOutput();

        assertThat(ioHandler.getOutputBuffer()).isEqualTo("You opened the door!");
    }
}
