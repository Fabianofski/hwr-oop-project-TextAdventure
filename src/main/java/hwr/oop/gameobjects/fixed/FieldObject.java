package hwr.oop.gameobjects.fixed;

import hwr.oop.Position;

public interface FieldObject {
    // TODO: Position obsolete? Position saved in FieldObject Array on Game class
    Position getPosition();
    void triggerEvent();
}
