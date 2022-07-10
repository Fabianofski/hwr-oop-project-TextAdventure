package hwr.oop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class IOHandlerTest {

    IIOHandler ioHandler;
    ByteArrayInputStream input;
    ByteArrayOutputStream output;
    String testInput = "TestInput";
    String testRequest = "TestRequest";
    String newLine;


    @BeforeEach
    void setUp() {
        input = new ByteArrayInputStream(testInput.getBytes());
        output = new ByteArrayOutputStream();
        newLine = System.getProperty("line.separator");

        ioHandler = new IOHandler(input, new PrintStream(output));
    }

    @Test
    void ioHandler_addToOutputBuffer_addsToIOHandlerAndGetsIOHandlerWithAddedInput() {
        ioHandler.addToOutputBuffer(testInput);
        String result = ioHandler.getOutputBuffer();

        assertThat(result).isEqualTo(testInput);
    }

    @Test
    void ioHandler_clearOutputBuffer_clearsIOHandler() {
        ioHandler.addToOutputBuffer(testInput);
        ioHandler.clearOutputBuffer();
        String result = ioHandler.getOutputBuffer();

        assertThat(result).isEqualTo("");
    }

    @Test
    void ioHandler_writeOutputAndClearBuffer_writesInputInOutputAndClearsBuffer() {
        ioHandler.addToOutputBuffer(testInput);

        ioHandler.writeOutputAndClearBuffer();
        String writtenOutput = output.toString();

        assertThat(writtenOutput).isEqualTo(testInput + newLine);

        String result = ioHandler.getOutputBuffer();
        assertThat(result).isEqualTo("");
    }


    @Test
    void ioHandler_requestStringInput_returnsTestInputResponseAndWritesRequestToOutput() {
        String response = ioHandler.requestStringInput(testRequest);
        String writtenOutput = output.toString();

        assertThat(response).isEqualTo(testInput);
        assertThat(writtenOutput).isEqualTo(testRequest + newLine);
    }

    @Test
    void ioHandler_requestIntegerInput_returnsTestInputResponseAndWritesRequestToOutput() {
        input = new ByteArrayInputStream("1 ".getBytes());
        ioHandler = new IOHandler(input, new PrintStream(output));

        int response = ioHandler.requestIntegerInput(testRequest);
        String writtenOutput = output.toString();

        assertThat(response).isEqualTo(1);
        assertThat(writtenOutput).isEqualTo(testRequest + newLine);
    }

    @Test
    void ioHandler_requestIntegerInputAndStringInput_returnsTestIntegerInputResponseSkipsNewLineAndReturnsTestStringInputResponse() {
        input = new ByteArrayInputStream(("1\n" + testInput).getBytes());
        ioHandler = new IOHandler(input, new PrintStream(output));

        int intResponse = ioHandler.requestIntegerInput(testRequest);
        String intWrittenOutput = output.toString();

        assertThat(intResponse).isEqualTo(1);
        assertThat(intWrittenOutput).isEqualTo(testRequest + newLine);

        String response = ioHandler.requestStringInput(testRequest);
        String writtenOutput = output.toString();

        assertThat(response).isEqualTo(testInput);
        assertThat(writtenOutput).isEqualTo(testRequest + newLine + testRequest + newLine);
    }
}
