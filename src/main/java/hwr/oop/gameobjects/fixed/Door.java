package hwr.oop.gameobjects.fixed;

import hwr.oop.IIOHandler;

public class Door implements FixedObject {
    IIOHandler ioHandler;

    public Door(IIOHandler ioHandler) {
        this.ioHandler = ioHandler;
    }
    @Override
    public String getObjectIcon() {
        return "Π";
    }

    @Override
    public void writeEventIOHandler() {
        ioHandler.addToOutputBuffer("\nYou don't have a Key to open the door.");
    }
}
