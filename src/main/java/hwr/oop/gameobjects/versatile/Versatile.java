package hwr.oop.gameobjects.versatile;

import hwr.oop.Position;

public interface Versatile {
    Position getPosition();
    void moveByAmount(Position amount);
}
