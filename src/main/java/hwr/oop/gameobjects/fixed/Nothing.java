package hwr.oop.gameobjects.fixed;

import hwr.oop.Position;

public class Nothing implements FieldObject {

    Position position = new Position();

    @Override
    public String getObjectIcon() {
        return " ";
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void triggerEvent() {

    }
}
