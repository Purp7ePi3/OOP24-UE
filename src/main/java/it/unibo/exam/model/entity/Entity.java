package it.unibo.exam.model.entity;

import java.awt.Rectangle;

public abstract class Entity {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Rectangle hitbox;
    
    // Basic constructor
    public Entity() {}
    
    // Getters
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public Rectangle getHitbox() { return hitbox; }
}