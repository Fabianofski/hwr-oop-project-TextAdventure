package hwr.oop;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class IOHandler implements IIOHandler {

    private StringBuilder outputBuffer;

    private final Scanner scanner;
    private final PrintStream out;

    public IOHandler(InputStream in, PrintStream out) {
        this.out = out;
        scanner = new Scanner(in);
        this.outputBuffer = new StringBuilder();
    }

    @Override
    public void addToOutputBuffer(String text) {
        outputBuffer.append(text);
    }

    @Override
    public void clearOutputBuffer() {
        outputBuffer = new StringBuilder();
    }

    @Override
    public String getOutputBuffer() {
        return outputBuffer.toString();
    }

    @Override
    public String requestStringInput(String request) {
        addToOutputBuffer(request);
        writeOutputAndClearBuffer();
        return scanner.nextLine();
    }

    @Override
    public int requestIntegerInput(String request) {
        addToOutputBuffer(request);
        writeOutputAndClearBuffer();
        int integer = scanner.nextInt();
        try{
            scanner.nextLine(); // Clear new Line from inputBuffer
        } catch (NoSuchElementException ignored){}
        return integer;
    }

    @Override
    public void writeOutputAndClearBuffer() {
        out.println(outputBuffer.toString());
        clearOutputBuffer();
    }
}
