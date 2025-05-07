package it.unibo.exam.model.entity;

import java.awt.Color;
import java.awt.Graphics2D;

import it.unibo.exam.view.panel.GamePanel;

public class Minigame4Gui {

    Minigame4Logic logic;
    Graphics2D g2;

    Minigame4Gui(Minigame4Logic logic) {
        this.logic = logic;
    }

    public void draw(Graphics2D g2) {
        // Minigioco attivo
        if (logic.isActive()) {
            logic.bottle().draw(g2);
            for (BallEntity ball : logic.balls()) {
                ball.draw(g2);
            }

            g2.setColor(Color.WHITE);
            g2.setFont(g2.getFont().deriveFont(18f));
            g2.drawString("Score: " + logic.score(), GamePanel.ORIGINAL_WIDTH / 2, 20);
        }
    }
    
}
