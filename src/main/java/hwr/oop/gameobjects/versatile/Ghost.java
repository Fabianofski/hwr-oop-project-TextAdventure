package hwr.oop.gameobjects.versatile;

import hwr.oop.Position;

public class Ghost implements VersatileObject {
    private Position position;
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

    public void setPosition(Position newPosition){
        this.position = newPosition;
    }


    @Override
    public void moveByAmount(Position amount) {
        position.add(amount);
    }

    public void moveTowardsPosition(Position towardPosition){
        int yDistance = position.getYDistance(towardPosition);
        int xDistance = position.getXDistance(towardPosition);

        if(Math.abs(xDistance) > Math.abs(yDistance))
            moveAlongXAxis(xDistance);
        else
            moveAlongYAxis(yDistance);
    }

    private void moveAlongYAxis(int yDistance) {
        if(yDistance > 0){
            position.add(new Position(0,-1));
        }
        else {
            position.add(new Position(0,1));
        }
    }

    private void moveAlongXAxis(int xDistance) {
        if(xDistance > 0){
            position.add(new Position(-1,0));
        }
        else{
            position.add(new Position(1,0));
        }
    }
}
