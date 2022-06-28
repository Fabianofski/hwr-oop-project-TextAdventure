package hwr.oop.gameobjects.fixed;

import hwr.oop.IOutputBuffer;
import hwr.oop.Position;

public class Nothing implements FixedObject {

    IOutputBuffer outputBuffer;

    public Nothing(IOutputBuffer outputBuffer) {
        this.outputBuffer = outputBuffer;
    }

    @Override
    public String getObjectIcon() {
        return " ";
    }

    @Override
    public void writeEventOutputBuffer() {
        outputBuffer.writeToOutputBuffer("\nNothing happens!");
    }
}
