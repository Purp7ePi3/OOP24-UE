package it.unibo.exam.model.entity;

import it.unibo.exam.Utility.Dimension;
import it.unibo.exam.Utility.Position;
import it.unibo.exam.view.panel.GamePanel;

public class NPC extends Entity {
    private String name;
    private String dialog;

    public NPC(String name, String dialog) {
        super(
            name,
            new Position(3 * GamePanel.ORIGINAL_WIDTH / 4, 3 * GamePanel.ORIGINAL_HEIGHT / 4), 
            new Dimension(GamePanel.TILE_SIZE, GamePanel.TILE_SIZE));
        this.name = name;
        this.dialog = dialog;
    }

    public String getName() {
        return name;
    }

    public String getDialog() {
        return dialog;
    }
    
}
