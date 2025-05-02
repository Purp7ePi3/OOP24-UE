package it.unibo.exam.model.entity;

import it.unibo.exam.Utility.Dimension;
import java.awt.Rectangle;

import it.unibo.exam.Utility.Position;
import it.unibo.exam.view.panel.GamePanel;

public class Door extends Entity {
    private String name;
    private int targetRoomIndex;
    private boolean isSolved;
    
    public Door(Position pos, String name, int targetRoomIndex, boolean isSolved) {
        super(name,pos , new Dimension(GamePanel.TILE_SIZE, GamePanel.TILE_SIZE));
        this.name = name;
        this.targetRoomIndex = targetRoomIndex;
        this.isSolved = isSolved;
        this.hitbox = new Rectangle(pos.x(), pos.y(), size.W(), size.H());
    }
    
    public int getTargetRoomIndex() {
        return targetRoomIndex;
    }
    
    public String getName() {
        return this.name;
    }

    
    public boolean isSolved() {
        return this.isSolved;
    }
    
    public void setSolved(boolean solved) {
        this.isSolved = solved;
    }

    public int getX() {
        return pos.x();
    }
    
    public int getY() {
        return pos.y();
    }
    
    public int getWidth() {
        return size.W();
    }
    
    public int getHeight() {
        return size.H();
    }
    
}