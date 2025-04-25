package it.unibo.exam.model.room.TheBar.view;

import it.unibo.exam.model.room.TheBar.model.BarPuzzleModel;
import it.unibo.exam.model.room.TheBar.model.Tube;
import java.awt.*;
import java.util.List;
import it.unibo.exam.view.panel.GamePanel;

public class BarPuzzleView implements BarPuzzleViewInterface {

    private final BarPuzzleModel model;

    private static final int TUBE_WIDTH = 60;
    private static final int TUBE_HEIGHT = 200;
    private static final int TUBE_SPACING = 40;
    private static final int LIQUID_HEIGHT = TUBE_HEIGHT / 4;

    public BarPuzzleView(BarPuzzleModel model) {
        this.model = model;
    }

    @Override
    public void draw(Graphics2D g2) {
        drawBackground(g2);
        drawTubes(g2);
        if (model.isPuzzleSolved()) {
            drawVictoryMessage(g2);
        }
    }

    private void drawBackground(Graphics2D g2) {
        g2.setColor(Color.DARK_GRAY);
        g2.fillRect(0, 0, GamePanel.ORIGINAL_WIDTH, GamePanel.ORIGINAL_HEIGHT);
    }

    private void drawTubes(Graphics2D g2) {
        List<Tube> tubes = model.getTubes();
        int selectedIndex = model.getSelectedTubeIndex();
        int startX = (GamePanel.ORIGINAL_WIDTH - (tubes.size() * (TUBE_WIDTH + TUBE_SPACING))) / 2;
        int y = GamePanel.ORIGINAL_HEIGHT / 2 - TUBE_HEIGHT / 2;

        for (int i = 0; i < tubes.size(); i++) {
            int x = startX + i * (TUBE_WIDTH + TUBE_SPACING);
            drawSingleTube(g2, tubes.get(i), x, y, i == selectedIndex);
        }
    }

    private void drawSingleTube(Graphics2D g2, Tube tube, int x, int y, boolean isSelected) {
        // Tube frame
        g2.setColor(Color.WHITE);
        g2.fillRect(x, y, TUBE_WIDTH, TUBE_HEIGHT);
        g2.setColor(Color.BLACK);
        g2.drawRect(x, y, TUBE_WIDTH, TUBE_HEIGHT);

        // Liquids inside
        List<Color> contents = tube.getContents();
        for (int j = 0; j < contents.size(); j++) {
            g2.setColor(contents.get(j));
            g2.fillRect(
                x + 5,
                y + TUBE_HEIGHT - (j + 1) * LIQUID_HEIGHT,
                TUBE_WIDTH - 10,
                LIQUID_HEIGHT
            );
        }

        if (isSelected) {
            drawSelectionHighlight(g2, x, y);
        }
    }

    private void drawSelectionHighlight(Graphics2D g2, int x, int y) {
        g2.setColor(Color.GREEN);
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(x - 5, y - 5, TUBE_WIDTH + 10, TUBE_HEIGHT + 10);
        g2.setStroke(new BasicStroke(1));
    }

    private void drawVictoryMessage(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 32));
        String message = "Puzzle Solved!";
        int textWidth = g2.getFontMetrics().stringWidth(message);
        g2.drawString(message, (GamePanel.ORIGINAL_WIDTH - textWidth) / 2, 100);
    }
}
