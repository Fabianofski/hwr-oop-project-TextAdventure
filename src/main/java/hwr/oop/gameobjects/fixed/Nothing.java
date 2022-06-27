package hwr.oop.gameobjects.fixed;

import hwr.oop.Position;

public class Nothing implements FixedObject {


    @Override
    public String getObjectIcon() {
        return " ";
    }

    @Override
    public void triggerEvent() {
        System.out.println("Nothing happens!");
    }
}
