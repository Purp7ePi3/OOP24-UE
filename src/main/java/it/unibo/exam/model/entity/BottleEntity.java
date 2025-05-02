package it.unibo.exam.model.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import it.unibo.exam.Utility.Dimension;
import it.unibo.exam.Utility.Position;

public class BottleEntity extends Entity {
    private final int speed = 7;

    /**
     * Legacy constructor (your existing one).
     * Call with: new BottleEntity("Bottle", x, y, width, height)
     */
    public BottleEntity(String name, int startX, int startY, int width, int height) {
        super(name, new Position(startX, startY), new Dimension(width, height));
        this.hitbox = new Rectangle(pos.x(), pos.y(), size.W(), size.H());
    }

    /**
     * New constructor mirroring BallEntity’s style.
     * Call with: new BottleEntity("Bottle", new Position(x,y), new Dimension(w,h))
     */
    public BottleEntity(String name, Position position, Dimension size) {
        super(name, position, size);
        this.hitbox = new Rectangle(position.x(), position.y(), size.W(), size.H());
    }

    public void moveLeft() {
        pos = new Position(Math.max(0, pos.x() - speed), pos.y());
        hitbox.setLocation(pos.x(), pos.y());
    }

    public void moveRight(int maxWidth) {
        pos = new Position(Math.min(maxWidth - size.W(), pos.x() + speed), pos.y());
        hitbox.setLocation(pos.x(), pos.y());
    }

    public boolean catchBall(BallEntity ball) {
        return hitbox.intersects(ball.getHitbox());
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.BLUE);
        g2.fillRect(pos.x(), pos.y(), size.W(), size.H());
    }
}
