package it.unibo.exam.model.entity;

import java.awt.Rectangle;

import it.unibo.exam.Utility.Dimension;
import it.unibo.exam.Utility.Position;
import it.unibo.exam.view.panel.GamePanel;

public abstract class Entity {
    protected Position pos;
    protected Dimension size;
    protected Rectangle hitbox;
    protected String name;
    
    // Basic constructor
    public Entity(String name, Position p, Dimension d) {
        this.name = name;
        this.pos = p;
        this.size = d;
    }
    
    // Getters
    public Position getPosition() { return pos; }
    public void updatePos(Position newP) { this.pos = newP; }
    public Dimension getSize() { return size; }
    public Rectangle getHitbox() { return hitbox; }
    public String getName() { return name; }

    public boolean isNearby(Entity otherEntity) {

        Position otherEntityPosition = otherEntity.getPosition();
        Dimension otherEntitySize = otherEntity.getSize();
        int detectionRange = GamePanel.TILE_SIZE;
        Rectangle detectionArea = new Rectangle(
                this.pos.x() - detectionRange / 2,
                this.pos.y() - detectionRange / 2,
                this.size.W() + detectionRange,
                this.size.H() + detectionRange
            );
        Rectangle otherEntityHitbox = new Rectangle(
                otherEntityPosition.x(),
                otherEntityPosition.y(),
                otherEntitySize.W(),
                otherEntitySize.H()
            );

        return detectionArea.intersects(otherEntityHitbox);
    }
}