package it.unibo.exam.model.room.TheBar.controller;

import it.unibo.exam.controller.input.KeyHandler;

public interface BarPuzzleControllerInterface {

    /**
     * Updates the controller based on player input.
     * @param keyHandler the keyboard input handler
     */
    void update(KeyHandler keyHandler);

    /**
     * Resets the current selection, if any.
     */
    void resetSelection();
}
