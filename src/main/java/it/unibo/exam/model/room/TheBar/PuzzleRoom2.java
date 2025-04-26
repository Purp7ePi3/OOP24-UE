package it.unibo.exam.model.room.TheBar;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import it.unibo.exam.controller.input.KeyHandler;
import it.unibo.exam.model.entity.Door;
import it.unibo.exam.model.game.GameState;
import it.unibo.exam.model.room.PuzzleRoom;
import it.unibo.exam.model.room.Room;
import it.unibo.exam.view.panel.GamePanel;

public class PuzzleRoom2 extends Room implements PuzzleRoom {
    
    private boolean puzzleSolved = false;
    private final GameState gameState;
    private final Image backgroundImage;

    public PuzzleRoom2(final List<Door> doors, final GameState gameState) {
        super(Color.GRAY, doors);
        this.gameState = gameState;

        Image img = null;
        try {
            img = ImageIO.read(new File(
                "src/main/java/it/unibo/exam/model/room/TheBar/view/png/BarRoom.png"
            ));
        } catch (IOException e) {
            System.err.println("❌ Could not load BarRoom.png: " + e.getMessage());
        }
        this.backgroundImage = img;
    }

    @Override
    public void updatePuzzleLogic(final KeyHandler keyHandler) {
        int playerX = gameState.getPlayer().getX();
        int playerY = gameState.getPlayer().getY();
        
        if (!getDoors().isEmpty()) {
            Door firstDoor = getDoors().get(0);
            
            if (playerX == 0 && playerY == 0 && keyHandler.interactPressed) {
                puzzleSolved = true;
                firstDoor.setSolved(true);
                gameState.updateDoorState(2, true); // 2 = index of this puzzle room
            }
        }
    }
    
    @Override
    public void draw(final Graphics2D g2) {
        // Draw background image if available
        if (backgroundImage != null) {
            g2.drawImage(backgroundImage, 0, 0, GamePanel.ORIGINAL_WIDTH, GamePanel.ORIGINAL_HEIGHT, null);
        } else {
            // fallback if image failed
            g2.setColor(getBackgroundColor());
            g2.fillRect(0, 0, GamePanel.ORIGINAL_WIDTH, GamePanel.ORIGINAL_HEIGHT);
        }
        
        // Draw doors
        for (Door door : getDoors()) {
            g2.setColor(door.isSolved() ? Color.GREEN : Color.RED);
            g2.fillRect(door.getX(), door.getY(), door.getWidth(), door.getHeight());
        }
        
        // If puzzle solved
        if (puzzleSolved) {
            g2.setColor(Color.WHITE);
            g2.setFont(g2.getFont().deriveFont(24f));
            g2.drawString("Puzzle Solved!", GamePanel.ORIGINAL_WIDTH / 2 - 80, GamePanel.ORIGINAL_HEIGHT / 2);
        }
    }
    
    @Override
    public boolean isPuzzleSolved() {
        return puzzleSolved;
    }
}
