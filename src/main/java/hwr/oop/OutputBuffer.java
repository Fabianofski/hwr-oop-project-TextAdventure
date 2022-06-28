package hwr.oop;

public class OutputBuffer implements IOutputBuffer{

    private StringBuilder outputBuffer;

    public OutputBuffer() {
        this.outputBuffer = new StringBuilder();
    }

    @Override
    public void writeToOutputBuffer(String text) {
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
}
