package hwr.oop.gameobjects.versatile;

import hwr.oop.Game;
import hwr.oop.IOHandler;
import hwr.oop.Position;

public class Player implements VersatileObject {
    private Position position;
    private int fieldSize;
    private Position[] viewDirections;
    private int currentViewDirection = 0;
    private boolean hasKeyInInventory;
    private Position lastposition;
    private boolean hasOpenedDoor;

    public int getLives() {
        return lives;
    }

    private int lives;

    public Player(Position position, int fieldSize){
        this.position = position;
        this.lastposition = position;
        this.fieldSize = fieldSize;
        this.lives = 3;
        viewDirections = new Position[]{
                new Position(0,1),
                new Position(-1,0),
                new Position(0,-1),
                new Position(1,0)
        };
    }
    public void restart(){
        lives = 3;
        hasKeyInInventory =false;
        hasOpenedDoor =false;
    }
    public boolean getHasOpenedDoor(){return hasOpenedDoor;}

    public void setHasOpenedDoor(boolean i){hasOpenedDoor=i;}

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

    public void turn(boolean turnRight){
        if(turnRight)
            currentViewDirection++;
        else
            currentViewDirection--;

        if(currentViewDirection < 0) currentViewDirection = 3;
        else if(currentViewDirection > 3) currentViewDirection  = 0;
    }

    @Override
    public Position getPosition() { return position;}

    @Override
    public String getObjectIcon() {
        Position viewDirection = viewDirections[currentViewDirection];

        if(viewDirection.getX() == 1) return ">";
        else if(viewDirection.getX() == -1) return "<";
        else if(viewDirection.getY() == -1) return "^";
        else return "V";
    }

    public void moveByAmount(int amount){
        lastposition = position;
        Position viewDirection = viewDirections[currentViewDirection];
        Position pos = new Position(viewDirection.getX() * amount, viewDirection.getY() * amount);
        moveByAmount(pos);
    }
    @Override
    public void moveByAmount(Position amountPos) {
        int size = fieldSize - 1;
        position.add(amountPos);
        position = new Position(clamp(position.getX(), 0, size), clamp(position.getY(), 0, size));
    }
    public void setPosition(Position position){
        this.position=new Position(position.getX(), position.getY());
    }

    private int clamp(int val, int min, int max) {
        return Math.max(min, Math.min(max, val));
    }
}
