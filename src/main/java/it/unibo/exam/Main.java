package it.unibo.exam;

import javax.swing.JFrame;
import it.unibo.exam.controller.game.GameController;
import it.unibo.exam.controller.input.KeyHandler;
import it.unibo.exam.controller.puzzle.puzzleController; // Add this import
import it.unibo.exam.model.game.GameState;
import it.unibo.exam.view.panel.GamePanel;

public final class Main {
    private Main() {
        throw new UnsupportedOperationException("Main class cannot be instantiated");
    }
    
    public static void main(final String[] args) {
        KeyHandler keyHandler = new KeyHandler();
        GameState gameState = new GameState();
        GameController gameController = new GameController(gameState, keyHandler);
        puzzleController puzzleController = new puzzleController(gameState, keyHandler); // Add this
        
        final JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("UNIBO");
        
        final GamePanel gamePanel = new GamePanel(gameController, puzzleController); // Update to pass PuzzleController
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamePanel.startGameThread();
        
    }
}