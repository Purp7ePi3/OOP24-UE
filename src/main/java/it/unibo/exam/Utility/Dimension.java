package it.unibo.exam.Utility;

public record Dimension(int width, int height) {
    public Dimension {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Width and height must be positive");
        }
    }

    public int W() {
        return width;
    }

    public int H() {
        return height;
    }
    
}
