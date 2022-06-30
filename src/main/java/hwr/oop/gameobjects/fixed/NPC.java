package hwr.oop.gameobjects.fixed;

import hwr.oop.IIOHandler;

public class NPC implements FixedObject {

    IIOHandler ioHandler;

    public NPC(IIOHandler ioHandler) {
        this.ioHandler = ioHandler;
    }

    @Override
    public String getObjectIcon() {
        return "Ω";
    }

    @Override
    public void writeEventToIOHandler() {
        String response = ioHandler.requestStringInput("\nWhats popping?");
        if(response.equals("Nothing")) ioHandler.addToOutputBuffer("\nOk.");
        else ioHandler.addToOutputBuffer("\nYep.");
    }
}
