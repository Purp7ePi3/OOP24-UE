package it.unibo.exam.model.entity;

import java.awt.Color;
import java.awt.Graphics2D;

import it.unibo.exam.Utility.Dimension;
import it.unibo.exam.Utility.Position;

public class BallEntity extends Entity {
    private final int speed = 4;

    public BallEntity(int startX, int radius) {
        super("Ball", new Position(startX, 0), new Dimension(radius, radius));
    }

    public void update() {
        pos.setY(pos.y() + speed);
        hitbox.setLocation(pos.x(), pos.y());
    }

    public boolean isOffScreen(int screenHeight) {
        return pos.y() > screenHeight;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.MAGENTA);
        g2.fillOval(pos.x(), pos.y(), size.W(), size.H());
    }
}
