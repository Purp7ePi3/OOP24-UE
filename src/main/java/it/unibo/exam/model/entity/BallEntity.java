package it.unibo.exam.model.entity;

import java.awt.Color;
import java.awt.Graphics2D;

public class BallEntity extends Entity {
    private final int speed = 4;

    public BallEntity(int startX, int radius) {
        this.x = startX;
        this.y = 0;
        this.width = radius;
        this.height = radius;
        this.hitbox = new java.awt.Rectangle(x, y, width, height);
    }

    public void update() {
        y += speed;
        hitbox.setLocation(x, y);
    }

    public boolean isOffScreen(int screenHeight) {
        return y > screenHeight;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.MAGENTA);
        g2.fillOval(x, y, width, height);
    }
}
