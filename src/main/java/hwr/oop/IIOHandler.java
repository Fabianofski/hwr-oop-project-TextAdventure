package hwr.oop;

public interface IIOHandler {

    void addToOutputBuffer(String text);

    void clearOutputBuffer();

    String getOutputBuffer();

    String requestStringInput(String request);

    int requestIntegerInput(String request);

    void writeOutputAndClearBuffer();

}
