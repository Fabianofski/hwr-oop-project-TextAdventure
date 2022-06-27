package hwr.oop.gameobjects.fixed;

import hwr.oop.Position;

public class Wall implements FixedObject {

    @Override
    public String getObjectIcon() {
        return "=";
    }


    @Override
    public void triggerEvent() {

    }
}
