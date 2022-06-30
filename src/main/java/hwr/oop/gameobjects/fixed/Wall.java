package hwr.oop.gameobjects.fixed;

import hwr.oop.IIOHandler;

public class Wall implements FixedObject {

    IIOHandler ioHandler;

    public Wall(IIOHandler ioHandler) {
        this.ioHandler = ioHandler;
    }

    @Override
    public String getObjectIcon() {
        return "#";
    }


    @Override
    public void writeEventToIOHandler() {
        ioHandler.addToOutputBuffer("\nYou shouldn't be stuck in the wall!");
    }
}
