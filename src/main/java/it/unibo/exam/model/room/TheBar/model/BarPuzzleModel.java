package it.unibo.exam.model.room.TheBar.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Model for the Bar puzzle minigame.
 * Manages tube contents, selection, and pour logic.
 */
public class BarPuzzleModel implements BarPuzzleModelInterface {

    private static final int CAPACITY = 4;  // max liquids per tube

    private final List<Tube> tubes;
    private int selectedTubeIndex = -1;     // -1 = no tube selected

    /**
     * Construct a puzzle with the given palette and empty tubes.
     * @param palette         distinct colors to use (each repeated 4×)
     * @param extraEmptyTubes how many extra empty tubes to include
     */
    public BarPuzzleModel(List<Color> palette, int extraEmptyTubes) {
        int nColors     = palette.size();
        int totalTubes  = nColors + extraEmptyTubes;

        // 1) Create all tubes (initially empty)
        tubes = new ArrayList<>(totalTubes);
        for (int i = 0; i < totalTubes; i++) {
            tubes.add(new Tube());
        }

        // 2) Build and shuffle the color pool (each palette color × CAPACITY)
        List<Color> pool = new ArrayList<>(nColors * CAPACITY);
        for (Color c : palette) {
            for (int i = 0; i < CAPACITY; i++) {
                pool.add(c);
            }
        }
        Collections.shuffle(pool, new Random());

        // 3) Distribute colors into the first nColors tubes
        int tubeIdx = 0;
        for (Color c : pool) {
            // skip to next tube if current is full
            while (tubes.get(tubeIdx).isFull()) {
                tubeIdx++;
            }
            tubes.get(tubeIdx).push(c);
        }
    }

    @Override
    public List<Tube> getTubes() {
        return Collections.unmodifiableList(tubes);
    }

    @Override
    public int getSelectedTubeIndex() {
        return selectedTubeIndex;
    }

    @Override
    public void selectTube(int index) {
        if (selectedTubeIndex < 0) {
            // first selection: mark it
            selectedTubeIndex = index;
        } else {
            // second selection: try to pour, then clear selection
            tryPour(selectedTubeIndex, index);
            selectedTubeIndex = -1;
        }
    }

    @Override
    public boolean tryPour(int fromIndex, int toIndex) {
        Tube src = tubes.get(fromIndex);
        Tube dst = tubes.get(toIndex);
        Color topSrc = src.peek();
        Color topDst = dst.peek();

        // nothing to pour or no space
        if (topSrc == null || dst.isFull()) {
            return false;
        }
        // can pour onto empty or same-color top
        if (topDst == null || topSrc.equals(topDst)) {
            dst.push(src.pop());
            return true;
        }
        return false;
    }

    @Override
    public boolean isPuzzleSolved() {
        // solved if every non-empty tube is uniform
        for (Tube t : tubes) {
            if (!t.isEmpty() && !t.isUniform()) {
                return false;
            }
        }
        return true;
    }
}
