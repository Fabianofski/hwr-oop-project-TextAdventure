package hwr.oop.gameobjects.versatile;

import hwr.oop.Position;

public class Ghost implements Versatile{
    private Position position = new Position();
    private Player player;

    public Ghost(Position position, Player player) {
        this.position = position;
        this.player = player;
    }

    public boolean ghostIsAtPlayer(){
        return position.equals(player.getPosition());
    }

    public void hitPlayerAndDealDamage(){
        player.harmPlayer(1);
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public String getObjectIcon() {
        return "G";
    }

    @Override
    public void moveByAmount(Position amount) {
        position.add(amount);
    }
}
