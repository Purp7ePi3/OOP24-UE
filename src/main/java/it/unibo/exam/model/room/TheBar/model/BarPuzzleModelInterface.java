package it.unibo.exam.model.room.TheBar.model;

import java.util.List;

public interface BarPuzzleModelInterface {

    /**
     * Returns the list of tubes representing the current puzzle state.
     * @return list of tubes
     */
    List<Tube> getTubes();

    /**
     * Returns the index of the currently selected tube.
     * @return selected tube index, or -1 if none selected
     */
    int getSelectedTubeIndex();

    /**
     * Selects a tube given its index.
     * If no tube was previously selected, stores the selection.
     * If one tube was already selected, tries to pour from it to the new selection.
     * @param index the index of the tube selected
     */
    void selectTube(int index);

    /**
     * Attempts to pour from one tube to another.
     * @param from index of source tube
     * @param to index of target tube
     * @return true if the pour was successful, false otherwise
     */
    boolean tryPour(int from, int to);

    /**
     * Checks if the puzzle is solved (all tubes uniform or empty).
     * @return true if puzzle is complete, false otherwise
     */
    boolean isPuzzleSolved();
}
