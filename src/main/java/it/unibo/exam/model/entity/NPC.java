package it.unibo.exam.model.entity;

import it.unibo.exam.Utility.Dimension;
import it.unibo.exam.Utility.Position;
import it.unibo.exam.view.panel.GamePanel;
import javax.swing.JOptionPane;

public class NPC extends Entity {
    private String name;
    private String dialog;
    private boolean isInteracting;
    private Minigame minigame;

    public NPC(String name, String dialog, Minigame minigame) {
        super(
            name,
            new Position(3 * GamePanel.ORIGINAL_WIDTH / 4, 3 * GamePanel.ORIGINAL_HEIGHT / 4), 
            new Dimension(GamePanel.TILE_SIZE, GamePanel.TILE_SIZE));
        this.isInteracting = false;
        this.name = name;
        this.dialog = dialog;
        this.minigame = minigame;
    }

    public String getName() {
        return name;
    }

    public String getDialog() {
        return dialog;
    }

    public void setInteraction() {
        this.isInteracting = true;
    }
    
    public void interact() {
        if (minigame != null) {
            minigame.start();
        }
    }
    
}
