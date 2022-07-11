package hwr.oop.gameobjects.fixed;

import hwr.oop.IIOHandler;
import hwr.oop.IOHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class WallTest {

    IIOHandler ioHandler;
    Wall wall;

    @BeforeEach
    void setUp() {
        ByteArrayInputStream input = new ByteArrayInputStream("TestResponse".getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        ioHandler = new IOHandler(input, new PrintStream(output));
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
