package it.unibo.exam.model.entity;

import java.awt.Rectangle;


import it.unibo.exam.Utility.Dimension;
import it.unibo.exam.Utility.Direction;
import it.unibo.exam.Utility.Position;
import it.unibo.exam.view.panel.GamePanel;
import it.unibo.exam.view.texture.AssetLoader;

@SuppressWarnings("unused")
public class Player extends Entity {
    private int speed;
    
    // Animation and direction properties
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    
    private int direction = DOWN; // Default direction
    private boolean moving = false;
    private boolean movingEnabler = true;
    
    // Animation variables
    private int spriteCounter = 0;
    private int spriteNum = 0;
    
    public Player(int x, int y, int speed) {
        super("Player", new Position(x, y) , new Dimension(GamePanel.TILE_SIZE, GamePanel.TILE_SIZE));
        this.speed = speed;
        this.hitbox = new Rectangle(x, y, size.W(), size.H());
    }
    
    public void move(boolean up, boolean down, boolean left, boolean right, double deltaTime) {
        // 1) Determine raw velocities
        int vx = 0, vy = 0;
        if (up)    { vy = -speed; direction = UP; moving = true; }
        if (down)  { vy =  speed; direction = DOWN; moving = true; }
        if (left)  { vx = -speed; direction = LEFT; moving = true; }
        if (right) { vx =  speed; direction = RIGHT; moving = true; }
    
        // 2) Compute frame‐scaled deltas
        int dx = (int)(vx * deltaTime * speed);
        int dy = (int)(vy * deltaTime * speed);
    
        // 3) Move X and clamp
        int newX = pos.x() + dx;
        newX = Math.max(0, Math.min(newX, GamePanel.ORIGINAL_WIDTH - size.W()));
        pos.setX(newX);
    
        // 4) Move Y and clamp
        int newY = pos.y() + dy;
        newY = Math.max(0, Math.min(newY, GamePanel.ORIGINAL_HEIGHT - size.H()));
        pos.setY(newY);
    
        // 5) Update hitbox
        updateHitbox();
    
        // 6) Animate sprite if moving
        if (moving) {
            spriteCounter++;
            if (spriteCounter >= 15) {
                spriteNum = (spriteNum == 0 ? 1 : 0);
                spriteCounter = 0;
            }
        }
    }
    
    
    public int getSpeed() {
        return speed;
    }
    
    public int getDirection() {
        return direction;
    }
    
    public boolean isMoving() {
        return moving;
    }
    
    public int getSpriteNum() {
        return spriteNum;
    }
    
    private void updateHitbox() {
        hitbox.x = pos.x();
        hitbox.y = pos.y();
    }
    
    // Added setters needed by GameController
    public void setX(int x) {
        pos.setX(x);
        updateHitbox();
    }
    
    public void setY(int y) {
        pos.setY(y);
        updateHitbox();
    }

    public void setMovingEnabler(boolean movingEnabler) {
        this.movingEnabler = movingEnabler;
    }

    public int getX() {
        return pos.x();
    }
    
    public int getY() {
        return pos.y();
    }
    
}