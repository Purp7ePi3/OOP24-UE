package it.unibo.exam;

import javax.swing.*;
import java.awt.*;
import it.unibo.exam.controller.game.GameController;
import it.unibo.exam.controller.input.KeyHandler;
import it.unibo.exam.controller.puzzle.PuzzleController;
import it.unibo.exam.model.game.GameState;
import it.unibo.exam.view.panel.GamePanel;
import it.unibo.exam.view.panel.MainMenuPanel;

public final class Main {
    private Main() {
        throw new UnsupportedOperationException("Main class cannot be instantiated");
    }

    public static void main(final String[] args) {
        KeyHandler keyHandler = new KeyHandler();
        GameState gameState = new GameState();
        GameController gameController = new GameController(gameState, keyHandler);
        PuzzleController puzzleController = new PuzzleController(gameState, keyHandler);
        // Get Max Size
        Rectangle screenBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        int maxWidth = screenBounds.width;
        int maxHeight = screenBounds.height;

        final JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setTitle("UNIBO");

        window.setSize(maxWidth, maxHeight);
        // Posiziona la finestra correttamente
        window.setLocation(screenBounds.x, screenBounds.y);

        GamePanel gamePanel = new GamePanel(gameController, puzzleController, maxWidth, maxHeight);
        MainMenuPanel mainMenuPanel = new MainMenuPanel(window, gameController, puzzleController, maxWidth, maxHeight);
        
        // Add main menu
        window.add(mainMenuPanel, BorderLayout.CENTER);

        window.setVisible(true);
    }
}
