package hwr.oop.gameobjects.fixed;

import hwr.oop.IOutputBuffer;
import hwr.oop.Position;

public class NPC implements FixedObject {

    IOutputBuffer outputBuffer;

    public NPC(IOutputBuffer outputBuffer) {
        this.outputBuffer = outputBuffer;
    }

    @Override
    public String getObjectIcon() {
        return "Ω";
    }

    @Override
    public void writeEventOutputBuffer() {
        outputBuffer.writeToOutputBuffer("\nTalking to NPC.");
    }
}
