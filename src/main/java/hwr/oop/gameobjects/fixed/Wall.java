package hwr.oop.gameobjects.fixed;

import hwr.oop.Position;

public class Wall implements FieldObject {

    Position position = new Position();
    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void triggerEvent() {

    }
}
