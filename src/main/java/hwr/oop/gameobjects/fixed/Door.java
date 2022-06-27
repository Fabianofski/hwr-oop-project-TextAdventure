package hwr.oop.gameobjects.fixed;

import hwr.oop.Position;

public class Door implements FixedObject {
    Position position = new Position();

    @Override
    public String getObjectIcon() {
        return "Π";
    }

    @Override
    public void triggerEvent() {

    }
}
