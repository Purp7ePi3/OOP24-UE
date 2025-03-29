package it.unibo.exam.model.room;

import it.unibo.exam.controller.input.KeyHandler;
import java.awt.Graphics2D;

public interface PuzzleRoom {
    void updatePuzzleLogic(KeyHandler keyHandler);
    boolean isPuzzleSolved();
    void draw(Graphics2D g2);
}