package hwr.oop.gameobjects.versatile;

import hwr.oop.Position;

public interface Versatile {
    Position getPosition();
    String getObjectIcon();

    void moveByAmount(Position amount);
}
