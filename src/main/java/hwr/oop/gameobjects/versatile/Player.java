package hwr.oop.gameobjects.versatile;

import hwr.oop.Position;

public class Player implements VersatileObject {
    private Position position;
    private Position[] viewDirections;
    private int currentViewDirection = 0;

    public int getLives() {
        return lives;
    }

    private int lives;

    public Player(Position position){
        this.position = position;
        this.lives = 3;
        viewDirections = new Position[]{
                new Position(0,1),
                new Position(-1,0),
                new Position(0,-1),
                new Position(1,0)
        };
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
    public Position getPosition() {
        return position;
    }

    @Override
    public String getObjectIcon() {
        Position viewDirection = viewDirections[currentViewDirection];

        if(viewDirection.getX() == 1) return ">";
        else if(viewDirection.getX() == -1) return "<";
        else if(viewDirection.getY() == -1) return "^";
        else return "V";
    }

    public void moveByAmount(int amount){
        Position viewDirection = viewDirections[currentViewDirection];
        Position pos = new Position(viewDirection.getX() * amount, viewDirection.getY() * amount);
        moveByAmount(pos);
    }

    @Override
    public void moveByAmount(Position amountPos) {
        position.add(amountPos);
    }
}
