package it.unibo.exam.model.room.TheBar.controller;

import java.util.List;
import it.unibo.exam.controller.input.KeyHandler;
import it.unibo.exam.model.room.TheBar.model.BarPuzzleModelInterface;
import it.unibo.exam.model.room.TheBar.model.Tube;

public class BarPuzzleController implements BarPuzzleControllerInterface {
    private final BarPuzzleModelInterface model;
    private int hoveredIndex   = 0;  // which tube the player is “on”
    private int selectedIndex  = -1; // -1 = none chosen as source

    public BarPuzzleController(BarPuzzleModelInterface model) {
        this.model = model;
    }

    @Override
    public void update(KeyHandler keyHandler) {
        List<Tube> tubes = model.getTubes();
        int n = tubes.size();

        // ← Move hover left
        if (keyHandler.leftPressed) {
            hoveredIndex = (hoveredIndex - 1 + n) % n;
            // prevent repeated rapid moves:
            keyHandler.leftPressed = false;
        }

        // → Move hover right
        if (keyHandler.rightPressed) {
            hoveredIndex = (hoveredIndex + 1) % n;
            keyHandler.rightPressed = false;
        }

        // E = select or pour
        if (keyHandler.interactPressed) {
            keyHandler.interactPressed = false;

            if (selectedIndex < 0) {
                // first press: choose the source tube
                selectedIndex = hoveredIndex;
            } else {
                // second press: attempt to pour into hovered tube
                model.tryPour(selectedIndex, hoveredIndex);
                // reset selection (even if pour failed)
                selectedIndex = -1;
            }
        }
    }

    @Override
    public void resetSelection() {
        selectedIndex = -1;
    }

    /** 
     * For the View: which tube to highlight as “hovered.”
     */
    public int getHoveredIndex() {
        return hoveredIndex;
    }

    /**
     * For the View: which tube is currently chosen as source (–1 if none).
     */
    public int getSelectedIndex() {
        return selectedIndex;
    }
}
