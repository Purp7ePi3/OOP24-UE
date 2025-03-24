package it.unibo.exam.model.entity;

import java.awt.Rectangle;
import it.unibo.exam.view.panel.GamePanel;

public class Player extends Entity {
    private int speed;
    
    // Animation and direction properties
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    
    private int direction = DOWN; // Default direction
    private boolean moving = false;
    
    // Animation variables
    private int spriteCounter = 0;
    private int spriteNum = 0;
    
    public Player(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.width = GamePanel.TILE_SIZE;
        this.height = GamePanel.TILE_SIZE;
        this.speed = speed;
        this.hitbox = new Rectangle(x, y, width, height);
    }
    
    public void move(boolean up, boolean down, boolean left, boolean right, double deltaTime) {
        int currentSpeedX = 0;
        int currentSpeedY = 0;
        
        // Reset moving flag
        moving = false;
        
        // Update direction based on key input
        if (up) {
            currentSpeedY = -speed;
            direction = UP;
            moving = true;
        }
        if (down) {
            currentSpeedY = speed;
            direction = DOWN;
            moving = true;
        }
        if (left) {
            currentSpeedX = -speed;
            direction = LEFT;
            moving = true;
        }
        if (right) {
            currentSpeedX = speed;
            direction = RIGHT;
            moving = true;
        }
        
        // Update position
        x += currentSpeedX * deltaTime * speed;
        y += currentSpeedY * deltaTime * speed;
        
        // Boundary checks
        if (x < 0) x = 0;
        if (x > GamePanel.ORIGINAL_WIDTH - width) x = GamePanel.ORIGINAL_WIDTH - width;
        if (y < 0) y = 0;
        if (y > GamePanel.ORIGINAL_HEIGHT - height) y = GamePanel.ORIGINAL_HEIGHT - height;
        
        updateHitbox();
        
        // Update animation
        if (moving) {
            spriteCounter += deltaTime * 10;
            if (spriteCounter > 1) {
                spriteNum = (spriteNum + 1) % 4; // Cycle through 4 frames
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
        hitbox.x = x;
        hitbox.y = y;
    }
    
    // Added setters needed by GameController
    public void setX(int x) {
        this.x = x;
        updateHitbox();
    }
    
    public void setY(int y) {
        this.y = y;
        updateHitbox();
    }
}