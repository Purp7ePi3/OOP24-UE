package it.unibo.exam.model.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import it.unibo.exam.Utility.Dimension;
import it.unibo.exam.Utility.Position;

public class BallEntity extends Entity {
    private final int speed = 4;

    /** 
     * Legacy constructor (your existing one): 
     * call with new BallEntity("Ball", x, radius)
     */
    public BallEntity(String name, int startX, int radius) {
        super(name, new Position(startX, 0), new Dimension(radius, radius));
        this.hitbox = new Rectangle(pos.x(), pos.y(), size.W(), size.H());
    }

    /**
     * New constructor mirroring BottleEntity’s style:
     * call with new BallEntity("Ball", new Position(x,y), new Dimension(w,h))
     */
    public BallEntity(String name, Position position, Dimension size) {
        super(name, position, size);
        this.hitbox = new Rectangle(position.x(), position.y(), size.W(), size.H());
    }

    public void update() {
        pos = new Position(pos.x(), pos.y() + speed);
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
