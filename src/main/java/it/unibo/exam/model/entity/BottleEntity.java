package it.unibo.exam.model.entity;

import java.awt.Color;
import java.awt.Graphics2D;

import it.unibo.exam.Utility.Dimension;
import it.unibo.exam.Utility.Position;

public class BottleEntity extends Entity {
    private final int speed = 7;

    public BottleEntity(int startX, int startY, int width, int height) {
        super("Bottle", new Position(startX, startY), new Dimension(width, height));
        this.hitbox = new java.awt.Rectangle(startX, startY, width, height);
    }

    public void moveLeft() {
        pos.setX(Math.max(0, pos.x() - speed));
        hitbox.setLocation(pos.x(), pos.y());
    }

    public void moveRight(int maxWidth) {
        pos.setX(Math.max(maxWidth , pos.x() + speed));
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
