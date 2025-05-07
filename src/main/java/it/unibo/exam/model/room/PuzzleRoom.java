package it.unibo.exam.model.room;

import java.awt.Color;
import java.awt.Graphics2D;

import it.unibo.exam.controller.input.KeyHandler;
import it.unibo.exam.model.entity.Minigame;

public interface PuzzleRoom {
    void updatePuzzleLogic(KeyHandler keyHandler);
    Color getBackgroundColor();
    boolean isPuzzleSolved();
    void draw(Graphics2D g2);
<<<<<<< Updated upstream
}
=======
    Minigame getMinigame(); // Add this method to retrieve the minigame
}
>>>>>>> Stashed changes
