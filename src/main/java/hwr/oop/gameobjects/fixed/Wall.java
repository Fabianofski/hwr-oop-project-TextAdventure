package hwr.oop.gameobjects.fixed;

import hwr.oop.IOutputBuffer;
import hwr.oop.OutputBuffer;
import hwr.oop.Position;

public class Wall implements FixedObject {

    IOutputBuffer outputBuffer;

    public Wall(IOutputBuffer outputBuffer) {
        this.outputBuffer = outputBuffer;
    }

    @Override
    public String getObjectIcon() {
        return "#";
    }


    @Override
    public void writeEventOutputBuffer() {
        outputBuffer.writeToOutputBuffer("\nYou shouldn't be stuck in the wall!");
    }
}
