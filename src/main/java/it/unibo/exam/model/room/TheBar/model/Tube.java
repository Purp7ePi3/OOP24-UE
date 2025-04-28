package it.unibo.exam.model.room.TheBar.model;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class Tube implements TubeInterface {

    private final Deque<Color> contents = new ArrayDeque<>();

    public Tube() {}

    // State Checks
    @Override
    public boolean isEmpty() {
        return contents.isEmpty();
    }

    @Override
    public boolean isFull() {
        return contents.size() >= CAPACITY;
    }

    @Override
    public int size() {
        return contents.size();
    }

    // Stack Behavior
    @Override
    public Color peek() {
        return contents.peek();
    }

    @Override
    public Color pop() {
        return contents.isEmpty() ? null : contents.pop();
    }

    @Override
    public void push(Color color) {
        if (contents.size() < TubeInterface.CAPACITY) {
            contents.push(color);   
        } else {
            throw new IllegalStateException("Tube is full, cannot push more liquids");
        }
    }

    // Utility
    @Override
    public boolean isUniform() {
        return contents.stream().distinct().count() <= 1;
    }

    @Override
    public List<Color> getContents() {
        List<Color> list = new ArrayList<>(contents);
        Collections.reverse(list);          // now index 0 is the bottom layer
        return list;
    }

}
