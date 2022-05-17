package hwr.oop.gameobjects.versatile;

import hwr.oop.Position;

public class Player implements Versatile{
    private Position position;

    public int getLives() {
        return lives;
    }

    private int lives;

    public Player(Position position){
        this.position = position;
        this.lives = 3;
    }

    public void harmPlayer(int damage){
        lives -= damage;
    }

    public boolean isDead(){
        return lives <= 0;
    }

    public void turn(boolean turnRight){
        // TODO: Turn Player -> change Viewdirection
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void moveByAmount(Position amount) {
        position.add(amount);
    }
}
