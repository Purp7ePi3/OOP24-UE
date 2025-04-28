package it.unibo.exam.model.room.TheBar;

import java.awt.*;
import java.awt.image.*;
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

// MVC classes for the puzzle
import it.unibo.exam.model.room.TheBar.model.BarPuzzleModel;
import it.unibo.exam.model.room.TheBar.view.BarPuzzleView;
import it.unibo.exam.model.room.TheBar.controller.BarPuzzleController;

@SuppressWarnings("unused")
public class PuzzleRoom2 extends Room implements PuzzleRoom {
    // ––––– World vs Puzzle toggle
    private boolean isPuzzleActive = false;

    // ––––– Room / GameState
    private final GameState gameState;
    private final Image roomBackgroundImage;

    // ––––– Puzzle MVC
    private BarPuzzleModel   puzzleModel;
    private BarPuzzleView    puzzleView;
    private BarPuzzleController puzzleController;

    public PuzzleRoom2(final List<Door> doors, final GameState gameState) {
        super(Color.GRAY, doors);
        this.gameState = gameState;

        // Load the BarRoom.png background
        Image img = null;
        try {
            img = ImageIO.read(new File(
                "src/main/java/it/unibo/exam/model/room/TheBar/view/png/BarRoom.png"
            ));
        } catch (IOException e) {
            System.err.println("❌ Could not load BarRoom.png: " + e.getMessage());
        }
        this.roomBackgroundImage = img;
    }

    @Override
    public void updatePuzzleLogic(final KeyHandler keyHandler) {
        if (!isPuzzleActive) {
            // ––––– Walking / world mode
            int px = gameState.getPlayer().getX();
            int py = gameState.getPlayer().getY();

            // If player presses E at (0,0), start the puzzle
            if (px == 0 && py == 0 && keyHandler.interactPressed) {
                isPuzzleActive = true;

                // Initialize Puzzle MVC
                // (choose your actual palette of colors here)
                List<Color> palette = List.of(
                    Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW
                );
                puzzleModel      = new BarPuzzleModel(palette, 2);
                puzzleController = new BarPuzzleController(puzzleModel);
                puzzleView       = new BarPuzzleView(puzzleModel, puzzleController);
                
            }

        } else {
            // ––––– Puzzle mode
            puzzleController.update(keyHandler);

            // When the puzzle is solved, unlock door and exit puzzle
            if (puzzleModel.isPuzzleSolved()) {
                // Unlock the back door (firstDoor)
                Door firstDoor = getDoors().get(0);
                firstDoor.setSolved(true);
                gameState.updateDoorState(2, true);  // 2 == this room’s index

                // Reset to world mode
                isPuzzleActive = false;
            }
        }
    }

    @Override
    public void draw(final Graphics2D g2) {
        if (!isPuzzleActive) {
            // ––––– Draw Bar Room world
            if (roomBackgroundImage != null) {
                g2.drawImage(
                    roomBackgroundImage,
                    0, 0,
                    GamePanel.ORIGINAL_WIDTH,
                    GamePanel.ORIGINAL_HEIGHT,
                    null
                );
            } else {
                g2.setColor(getBackgroundColor());
                g2.fillRect(0, 0, GamePanel.ORIGINAL_WIDTH, GamePanel.ORIGINAL_HEIGHT);
            }

            // Draw doors as usual
            for (Door door : getDoors()) {
                g2.setColor(door.isSolved() ? Color.GREEN : Color.RED);
                g2.fillRect(door.getX(), door.getY(), door.getWidth(), door.getHeight());
            }

        } else {
            // ––––– Delegate to the Puzzle View
            puzzleView.draw(g2);
        }
    }

    @Override
    public boolean isPuzzleSolved() {
        // This method is only used by the engine if you need it;
        // inside this class we handle puzzleSolved via the model.
        return !isPuzzleActive && getDoors().get(0).isSolved();
    }
}
