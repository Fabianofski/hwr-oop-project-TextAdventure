package hwr.oop;

import java.util.Objects;

public class Position {
    int x;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    int y;

    public Position(){
        this(0,0);
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void add(Position pos){
        x += pos.x;
        y += pos.y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return y == position.y && x == position.x;
    }

    public int distance(Position two){
        return Math.abs(getXdistance(two))+Math.abs(getYdistance(two));
    }
    public int getYdistance(Position pos){
        return this.y-pos.y;
    }
    public int getXdistance(Position pos){
        return this.x-pos.x;
    }

    @Override
    public int hashCode() {
        return Objects.hash(y, x);
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ",y=" + y +
                '}';
    }


}
