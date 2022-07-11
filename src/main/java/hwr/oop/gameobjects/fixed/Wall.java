package hwr.oop.gameobjects.fixed;

import hwr.oop.Game;
import hwr.oop.IIOHandler;
import hwr.oop.Position;
import hwr.oop.gameobjects.versatile.Player;

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
    public void addEventToOutput() {
        ioHandler.addToOutputBuffer("\nYou shouldn't be stuck in the wall!");
    }
}
