package it.unibo.exam.view.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import it.unibo.exam.controller.game.GameController;
import it.unibo.exam.controller.puzzle.puzzleController; // Add this import
import it.unibo.exam.model.entity.Player;
import it.unibo.exam.model.game.GameState;
import it.unibo.exam.model.room.PuzzleRoom;
import it.unibo.exam.model.room.Room;
import it.unibo.exam.view.renderer.EntityRenderer;
import it.unibo.exam.view.renderer.RoomRenderer;

public class GamePanel extends JPanel implements Runnable {
    public static final int ORIGINAL_WIDTH = 800;
    public static final int ORIGINAL_HEIGHT = 600;
    static final int ORIGINAL_TILE_SIZE = 16;
    static final int SCALE = 3;
    public static final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;
    
    private Thread gameThread;
    private GameController gameController;
    private puzzleController puzzleController; // Add this field
    private RoomRenderer roomRenderer;
    private EntityRenderer entityRenderer;
    
    public GamePanel(GameController gameController, puzzleController puzzleController) { // Update constructor
        this.gameController = gameController;
        this.puzzleController = puzzleController; // Initialize
        this.roomRenderer = new RoomRenderer();
        this.entityRenderer = new EntityRenderer();
        
        this.setPreferredSize(new Dimension(ORIGINAL_WIDTH, ORIGINAL_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(gameController.getKeyHandler());
        this.setFocusable(true);
    }
    
    public void startGameThread() {
        if (gameThread == null) {
            gameThread = new Thread(this);
            gameThread.start();
        }
    }
    
    @Override
    public void run() {
        final int FPS = 60;
        final long frameTime = 1_000_000_000 / FPS;
        long lastTime = System.nanoTime();
        double deltaTime;
        
        gameController.setLastFpsTime(System.currentTimeMillis());
        gameController.resetFrameCount();
        
        while (gameThread != null) {
            long currentTime = System.nanoTime();
            deltaTime = (currentTime - lastTime) / 1_000_000_000.0;
            
            gameController.update(deltaTime);
            puzzleController.update(deltaTime); // Add this to update puzzle logic
            gameController.updateFPS();
            repaint();
            
            long sleepTime = frameTime - (System.nanoTime() - currentTime);
            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime / 1_000_000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            lastTime = currentTime;
        }
    }
    
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2 = (Graphics2D) g;
        
        GameState gameState = gameController.getGameState();
        Room currentRoom = gameState.getCurrentRoom();
        Player player = gameState.getPlayer();
        
        if (currentRoom instanceof PuzzleRoom) {
            ((PuzzleRoom) currentRoom).draw(g2);
        } else {
            roomRenderer.render(g2, currentRoom);
        }
        
        entityRenderer.renderPlayer(g2, player);
        drawUI(g2, gameState);
        
        g2.dispose();
    }
    
    private void drawUI(Graphics2D g2, GameState gameState) {
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(20f));
        FontMetrics metrics = g2.getFontMetrics();
        
        String fpsText = "FPS: " + gameController.getFPS();
        g2.drawString(fpsText, getWidth() - metrics.stringWidth(fpsText) - 20, 30);
        
        String lastInteraction = gameState.getLastInteraction();
        if (!lastInteraction.isEmpty()) {
            int textWidth = metrics.stringWidth(lastInteraction);
            int textX = (getWidth() - textWidth) / 2;
            g2.drawString(lastInteraction, textX, 60);
        }
        
        String roomIndexText = "Current Room Index: " + gameState.getCurrentRoomIndex();
        g2.drawString(roomIndexText, 20, 30);
    }
}