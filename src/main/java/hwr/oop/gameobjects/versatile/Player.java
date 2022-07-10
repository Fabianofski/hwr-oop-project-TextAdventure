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

    public void turn(String direction){
        if(direction.equals("1")) { 
           this.currentViewDirection = 1;
        }
        if(direction.equals("2")) {
            currentViewDirection = 3;
            System.out.println("hi");
        }
        if(direction.equals("3")) {
            currentViewDirection = 2;
        }
        if(direction.equals("4")) {
            currentViewDirection = 0;
        }

        System.out.println(getObjectIcon());

    }

    @Override
    public Position getPosition() { return position;}

    @Override
    public String getObjectIcon() {
        if(currentViewDirection==3) return ">";
        else if(currentViewDirection==1) return "<";
        else if(currentViewDirection==2) return "^";
        else return "V";
    }

    public Position moveByAmount(int amount){
        lastposition = position;
        Position viewDirection = viewDirections[currentViewDirection];
        Position pos = new Position(viewDirection.getX() * amount, viewDirection.getY() * amount);
        moveByAmount(pos);
        return  pos;
    }
    @Override
    public void moveByAmount(Position amountPos) {
        int size = fieldSize - 1;
        position.add(amountPos);

        int clampedX = clamp(position.getX(), 0, size);
        int clampedY = clamp(position.getY(), 0, size);
        position = new Position(clampedX, clampedY);
    }
    public void setPosition(Position position){
        this.position=position;
    }

    private int clamp(int val, int min, int max) {
        return Math.max(min, Math.min(max, val));
    }
}
