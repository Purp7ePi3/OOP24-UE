package it.unibo.exam.model.room.TheBar.model;

import java.awt.Color;
import java.util.List;

public interface TubeInterface {
    
     /** Max number of liquid levels in a tube */
     public static final int CAPACITY = 4;

    /**
     * Checks if the tube is empty.
     * @return true if no liquids are in the tube.
     */
    boolean isEmpty();
    
    /**
     * Checks if the tube has reached its maximum capacity.
     * @return true if the tube is full.
     */
    boolean isFull();
    
    /**
     * Returns the color of the top liquid without removing it.
     * @return the top color, or null if the tube is empty.
     */
    Color peek();
    
    /**
     * Removes and returns the top liquid.
     * @return the removed color, or null if the tube is empty.
     */
    Color pop();
    
    /**
     * Adds a new liquid to the top of the tube.
     * @param color the color to add.
     */
    void push(Color color);
    
    /**
     * Checks if all liquids in the tube are the same color.
     * @return true if uniform or empty.
     */
    boolean isUniform();
    
    /**
     * Returns the current number of liquid levels in the tube.
     * @return the number of liquids.
     */
    int size();
    
    /**
     * Gets the full list of liquid colors, from bottom to top.
     * Used for drawing the tube visually.
     * @return the list of liquid colors.
     */
    List<Color> getContents();
}
