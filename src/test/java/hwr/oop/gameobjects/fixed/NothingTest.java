package hwr.oop.gameobjects.fixed;

import hwr.oop.IIOHandler;
import hwr.oop.IOHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class NothingTest {

    IIOHandler ioHandler;
    Nothing nothing;

    @BeforeEach
    void setUp() {
        ByteArrayInputStream input = new ByteArrayInputStream("TestResponse".getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        ioHandler = new IOHandler(input, new PrintStream(output));
        nothing = new Nothing(ioHandler);
    }

    @Test
    void nothing_isFieldObject() {
        nothing = new Nothing(ioHandler);
        assertThat(nothing)
                .isInstanceOf(FixedObject.class)
                .isInstanceOf(Nothing.class);
    }

    @Test
    void getObjectIcon_iconIsUnderscore() {
        String icon = nothing.getObjectIcon();
        assertThat(icon).isEqualTo("_");
    }

    @Test
    void addEventToOutput_printsNothingToConsole() {
        nothing.addEventToOutput();
        assertThat(ioHandler.getOutputBuffer()).isEqualTo("");
    }
}
