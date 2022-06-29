package hwr.oop.gameobjects.fixed;

import hwr.oop.IIOHandler;

public class Nothing implements FixedObject {

    IIOHandler ioHandler;

    public Nothing(IIOHandler ioHandler) {
        this.ioHandler = ioHandler;
    }

    @Override
    public String getObjectIcon() {
        return "_";
    }

    @Override
    public void writeEventIOHandler() {
        ioHandler.addToOutputBuffer("\nNothing happens!");
    }
}
