package hwr.oop;

import java.util.Objects;

public class Position {
    int x;
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

    public int distance(Position pos){
        return Math.abs(getXDistance(pos)) + Math.abs(getYDistance(pos));
    }

    public int getYDistance(Position pos){
        return this.y - pos.y;
    }

    public int getXDistance(Position pos){
        return this.x - pos.x;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return y == position.y && x == position.x;
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
