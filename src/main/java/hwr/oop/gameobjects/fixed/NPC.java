package hwr.oop.gameobjects.fixed;

import hwr.oop.Position;

public class NPC implements FixedObject {

    @Override
    public String getObjectIcon() {
        return "Ω";
    }

    @Override
    public void triggerEvent() {
        System.out.println("Talking to NPC.");
    }
}
