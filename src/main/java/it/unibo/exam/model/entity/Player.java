package it.unibo.exam.model.entity;

import java.awt.Rectangle;


import it.unibo.exam.Utility.Dimension;
import it.unibo.exam.Utility.Direction;
import it.unibo.exam.Utility.Position;
import it.unibo.exam.view.panel.GamePanel;
import it.unibo.exam.view.texture.AssetLoader;

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
        int currentSpeedX = 0;
        int currentSpeedY = 0;

        if(movingEnabler==true){
            // Reset moving flag
        moving = false;

        // Update direction and speed based on key input
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

        int dx = ( int) (currentSpeedX * deltaTime * speed);
        int dy = ( int) (currentSpeedY * deltaTime * speed);

        updatePos(pos.move(new Direction(dx, dy)));

        // Log updated position for debugging
        System.out.println("Updated Player position: x=" + pos.x() + ", y=" + pos.y());

        // Boundary checks
        if (pos.x() < 0) {
            pos.setX(0);
        }
        if (pos.x() > GamePanel.ORIGINAL_WIDTH - size.W()) {
            pos.setX(GamePanel.ORIGINAL_WIDTH - size.W());
        }
        if (pos.y() < 0) {
            pos.setY(0);
        }
        if (pos.y() > GamePanel.ORIGINAL_HEIGHT - size.H()) {
            pos.setY(GamePanel.ORIGINAL_HEIGHT - size.H());
        }

        // Update hitbox
        updateHitbox();

        // Update animation
        if(moving){
            spriteCounter++;
            if (spriteCounter >= 15) {
                
                if(spriteNum == 0){
                    spriteNum = 1;
                }
                else if(spriteNum == 1){
                    spriteNum = 0;
                }

                spriteCounter = 0;
            }
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
}