package it.unibo.exam.Utility;

public record Direction(int dx, int dy) {

    public static final Direction UP = new Direction(0, -1);
    public static final Direction DOWN = new Direction(0, 1);
    public static final Direction LEFT = new Direction(-1, 0);
    public static final Direction RIGHT = new Direction(1, 0);

    public int dX() {
        return dx;
    }

    public int dY() {
        return dy;
    }

    public Direction opposite() {
        return new Direction(-dx, -dy);
    }

    public Direction toVector(int module) {
        return new Direction(this.dx * module, this.dy * module);
    }
    
}
