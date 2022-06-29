package hwr.oop.gameobjects.versatile;

import hwr.oop.Game;
import hwr.oop.Position;

public class Ghost implements VersatileObject {
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

    public void setStartPosition(Position amountPos){
        this.position = amountPos;
    }


    @Override
    public void moveByAmount(Position playerpos) {
        if(Math.abs(this.getPosition().getYdistance(playerpos))<Math.abs(this.getPosition().getXdistance(playerpos))){
            if(this.getPosition().getXdistance(playerpos)>0){
                this.position= new Position(this.getPosition().getX()-1,this.position.getY());
            }
            else{
                this.position= new Position(this.getPosition().getX()+1,this.position.getY());
            }
        }
        else{
            if(this.getPosition().getYdistance(playerpos)>0){
                this.position = new Position(this.getPosition().getX(), this.position.getY()-1);
            }
            else {
                this.position = new Position(this.getPosition().getX(), this.position.getY()+1);
            }
        }

    }
}
