package it.unibo.exam.Utility;

public class Position{
    private int x;
    private int y;

    public Position(int x, int y) {
        if (x < 0 || y < 0) {
            this.x = 0;
            this.y = 0;
        } else {
            this.x = x;
            this.y = y;
        }
        
    }

    public int x() {
        return x;
    }
    public int y() {
        return y;
    }

    public void setX(int newX) {
        this.x = newX;
    }

    public void setY(int newY) {
        this.y = newY;
    }

    public Position move(Direction direction) {
        return new Position(x + direction.dx(), y + direction.dy());
    }

    public Position move(Position other) {
        return new Position(x + other.x, y + other.y);
    }

    public void ceckkBounds(Dimension d) {
        if (x < 0 || x >= d.W() || y < 0 || y >= d.H()) {
            throw new IllegalArgumentException("Position out of bounds");
        }
    }
}

