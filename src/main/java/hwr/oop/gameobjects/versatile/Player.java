package hwr.oop.gameobjects.versatile;

import hwr.oop.Position;

public class Player implements VersatileObject {
    private final int fieldSize;
    private final Position[] viewDirections;
    private int currentViewDirection = 4;
    private Position position;
    private boolean hasKeyInInventory;
    private boolean hasOpenedDoor;

    public int getLives() {
        return lives;
    }

    private int lives;

    public Player(Position position, int fieldSize){
        this.position = position;
        this.fieldSize = fieldSize;
        this.lives = 3;
        viewDirections = new Position[]{
                new Position(-1,0),
                new Position(0,-1),
                new Position(1,0),
                new Position(0,1)
        };
    }

    public boolean getHasOpenedDoor(){return hasOpenedDoor;}

    public void setHasOpenedDoor(boolean value){hasOpenedDoor = value;}

    public void giveKey(){
        hasKeyInInventory = true;
    }

    public boolean hasKey(){
        return hasKeyInInventory;
    }

    public void harmPlayer(int damage){
        lives -= damage;
    }

    public boolean isDead(){
        return lives <= 0;
    }

    public void turn(int direction) {
        currentViewDirection = clamp(direction, 1, 4);
    }

    @Override
    public Position getPosition() { return position;}

    @Override
    public String getObjectIcon() {
        if(currentViewDirection==1) return "<";
        else if(currentViewDirection==2) return "^";
        else if(currentViewDirection==3) return ">";
        else return "V";
    }

    public void moveByAmount(int amount){
        Position viewDirection = viewDirections[currentViewDirection - 1];
        Position pos = new Position(viewDirection.getX() * amount, viewDirection.getY() * amount);
        moveByAmount(pos);
    }
    @Override
    public void moveByAmount(Position amountPos) {
        position.add(amountPos);

        int clampedX = clamp(position.getX(), 0, fieldSize - 1);
        int clampedY = clamp(position.getY(), 0, fieldSize - 1);
        position = new Position(clampedX, clampedY);
    }
    public void setPosition(Position position){
        this.position=position;
    }

    private int clamp(int val, int min, int max) {
        return Math.max(min, Math.min(max, val));
    }
}
