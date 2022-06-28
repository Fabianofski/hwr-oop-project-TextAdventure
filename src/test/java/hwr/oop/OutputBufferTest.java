package hwr.oop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class OutputBufferTest {

    IOutputBuffer outputBuffer;

    @BeforeEach
    void setUp() {
        outputBuffer = new OutputBuffer();
    }

    @Test
    void outputBuffer_writeToOutputBuffer_writesToOutputBuffer() {
        outputBuffer.writeToOutputBuffer("Test");
        String result = outputBuffer.getOutputBuffer();

        assertThat(result).isEqualTo("Test");
    }

    @Test
    void outputBuffer_clearOutputBuffer_clearsOutputBuffer() {
        outputBuffer.writeToOutputBuffer("Test");
        outputBuffer.clearOutputBuffer();
        String result = outputBuffer.getOutputBuffer();

        assertThat(result).isEqualTo("");
    }
}
