package it.unibo.exam.model.entity;

import java.awt.Graphics2D;


import it.unibo.exam.controller.input.KeyHandler;
import it.unibo.exam.model.game.GameState;

public abstract class Minigame {

    private boolean isActive = false;
    private boolean isSolved = false;


    public abstract void update(KeyHandler keyHandler, GameState gameState);
    public abstract void drawGame(Graphics2D g2);

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public void setSolved(boolean isSolved) {
        this.isSolved = isSolved;
    }

    public void reset() {
        isActive = false;
        isSolved = false;
    }
}
