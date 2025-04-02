package it.unibo.exam.view.panel;

import java.awt.*;
import javax.swing.JPanel;
import it.unibo.exam.controller.game.GameController;
import it.unibo.exam.controller.puzzle.PuzzleController;
import it.unibo.exam.model.entity.Player;
import it.unibo.exam.model.game.GameState;
import it.unibo.exam.model.room.PuzzleRoom;
import it.unibo.exam.model.room.Room;
import it.unibo.exam.view.renderer.EntityRenderer;
import it.unibo.exam.view.renderer.RoomRenderer;

public class GamePanel extends JPanel implements Runnable {
    public static final int ORIGINAL_WIDTH = 1920;
    public static final int ORIGINAL_HEIGHT = 1080;
    static final int ORIGINAL_TILE_SIZE = 16;
    static final int SCALE = 3;
    public static final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;
    
    private Thread gameThread;
    private GameController gameController;
    private PuzzleController puzzleController;
    private RoomRenderer roomRenderer;
    private EntityRenderer entityRenderer;

    // Get screen size
    private static final Rectangle SCREEN_BOUNDS = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
    public static final int MAX_WIDTH = SCREEN_BOUNDS.width;
    public static final int MAX_HEIGHT = SCREEN_BOUNDS.height;

    // Add scale factors for resizing
    private double scaleX = 1.0;
    private double scaleY = 1.0;
    
    public GamePanel(GameController gameController, PuzzleController puzzleController, int maxWidth, int maxHeight) {
        this.gameController = gameController;
        this.puzzleController = puzzleController;
        this.roomRenderer = new RoomRenderer();
        this.entityRenderer = new EntityRenderer();
        
        this.setPreferredSize(new Dimension(maxWidth, maxHeight));
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
            
            updateScaleFactors();
            gameController.update(deltaTime);
            puzzleController.update(deltaTime);
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
    
    private void updateScaleFactors() {
        scaleX = (double) getWidth() / ORIGINAL_WIDTH;
        scaleY = (double) getHeight() / ORIGINAL_HEIGHT;
    }
    
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2 = (Graphics2D) g;
        
        // Apply scale transformations
        g2.scale(scaleX, scaleY);
        
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
        g2.drawString(fpsText, ORIGINAL_WIDTH - metrics.stringWidth(fpsText) - 20, 30);
        
        String lastInteraction = gameState.getLastInteraction();
        if (!lastInteraction.isEmpty()) {
            int textWidth = metrics.stringWidth(lastInteraction);
            int textX = (ORIGINAL_WIDTH - textWidth) / 2;
            g2.drawString(lastInteraction, textX, 60);
        }
        
        String roomIndexText = "Current Room: " + gameState.getCurrentRoomIndex();
        g2.drawString(roomIndexText, 20, 30);
    }
    
    // Method to convert screen coordinates to game coordinates
    public int getGameCoordX(int screenX) {
        return (int)(screenX / scaleX);
    }
    
    public int getGameCoordY(int screenY) {
        return (int)(screenY / scaleY);
    }
}