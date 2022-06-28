package hwr.oop.gameobjects.fixed;

import hwr.oop.IOutputBuffer;
import hwr.oop.Position;

public class Door implements FixedObject {
    IOutputBuffer outputBuffer;

    public Door(IOutputBuffer outputBuffer) {
        this.outputBuffer = outputBuffer;
    }
    @Override
    public String getObjectIcon() {
        return "Π";
    }

    @Override
    public void writeEventOutputBuffer() {
        outputBuffer.writeToOutputBuffer("\nYou don't have a Key to open the door.");
    }
}
