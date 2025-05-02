package it.unibo.exam.model.entity;

import java.awt.Color;
import java.awt.Graphics2D;

public class BottleEntity extends Entity {
    private final int speed = 7;

    public BottleEntity(int startX, int startY, int width, int height) {
        this.x = startX;
        this.y = startY;
        this.width = width;
        this.height = height;
        this.hitbox = new java.awt.Rectangle(x, y, width, height);
    }

    public void moveLeft() {
        x = Math.max(0, x - speed);
        hitbox.setLocation(x, y);
    }

    public void moveRight(int maxWidth) {
        x = Math.min(maxWidth - width, x + speed);
        hitbox.setLocation(x, y);
    }

    public boolean catchBall(BallEntity ball) {
        return hitbox.intersects(ball.getHitbox());
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.BLUE);
        g2.fillRect(x, y, width, height);
    }
}
