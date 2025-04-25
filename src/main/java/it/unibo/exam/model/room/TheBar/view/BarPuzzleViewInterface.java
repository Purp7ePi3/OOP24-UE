package it.unibo.exam.model.room.TheBar.view;

import java.awt.Graphics2D;

public interface BarPuzzleViewInterface {

    /**
     * Renders the entire minigame view based on the model's state.
     * Called by the game loop or room to display the puzzle.
     * 
     * @param g2 the Graphics2D context to draw on
     */
    void draw(Graphics2D g2);

    // Optional for debugging purposes during development
    // void drawDebug(Graphics2D g2);
}
