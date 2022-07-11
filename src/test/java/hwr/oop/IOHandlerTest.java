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
    void addToOutputBuffer_addTestInputToOutput_getOutputBufferReturnsTestInput() {
        ioHandler.addToOutputBuffer(testInput);
        String result = ioHandler.getOutputBuffer();

        assertThat(result).isEqualTo(testInput);
    }

    @Test
    void clearOutputBuffer_addTestInputToOutputAndClearOutputBuffer_getOutputBufferReturnsEmptyString() {
        ioHandler.addToOutputBuffer(testInput);
        ioHandler.clearOutputBuffer();
        String result = ioHandler.getOutputBuffer();

        assertThat(result).isEqualTo("");
    }

    @Test
    void writeOutputAndClearBuffer_addTestInputToBufferAndWriteAndClearBuffer_writesInputToOutputAndClearsBuffer() {
        ioHandler.addToOutputBuffer(testInput);

        ioHandler.writeOutputAndClearBuffer();
        String writtenOutput = output.toString();
        assertThat(writtenOutput).isEqualTo(testInput + newLine);

        String result = ioHandler.getOutputBuffer();
        assertThat(result).isEqualTo("");
    }


    @Test
    void requestStringInput_StringInInputStreamAndTestRequest_ReadsStringFromInputStreamAndWritesTestRequestToOutput() {
        String response = ioHandler.requestStringInput(testRequest);
        String writtenOutput = output.toString();

        assertThat(response).isEqualTo(testInput);
        assertThat(writtenOutput).isEqualTo(testRequest + newLine);
    }

    @Test
    void requestIntegerInput_IntegerInInputStreamAndTestRequest_ReadsIntegerFromInputStreamAndWritesTestRequestToOutput() {
        input = new ByteArrayInputStream("1 ".getBytes());
        ioHandler = new IOHandler(input, new PrintStream(output));

        int response = ioHandler.requestIntegerInput(testRequest);
        String writtenOutput = output.toString();

        assertThat(response).isEqualTo(1);
        assertThat(writtenOutput).isEqualTo(testRequest + newLine);
    }

    @Test
    void requestIntegerInputAndStringInput_outputsTestIntegerInputResponseSkipsNewLineAndReturnsTestStringInputResponse() {
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
