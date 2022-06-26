package hwr.oop.gameobjects.fixed;

import hwr.oop.Position;

public class Door implements FieldObject {
    Position position = new Position();

    @Override
    public String getObjectIcon() {
        return "Π";
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void triggerEvent() {

    }
}
