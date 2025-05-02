package it.unibo.exam.controller.game;

import it.unibo.exam.controller.input.KeyHandler;
import it.unibo.exam.model.entity.Door;
import it.unibo.exam.model.entity.NPC;
import it.unibo.exam.model.entity.Player;
import it.unibo.exam.model.game.GameState;
import it.unibo.exam.model.room.Room;
import it.unibo.exam.view.panel.GamePanel;
import javax.swing.JOptionPane;
import it.unibo.exam.view.panel.MainMenuPanel;
import it.unibo.exam.view.texture.AssetLoader;

/**
 * GameController is responsible for managing the game state and player interactions.
 * It updates the game state based on player input and handles room transitions.
 */
@SuppressWarnings("unused")
public class GameController {

    private GameState gameState;
    private KeyHandler keyHandler;
    private int fps;
    private long lastFpsTime;
    private int frameCount;
    private long lastUpdateTime;

    public GameController(GameState gameState, KeyHandler keyHandler) {
        this.gameState = gameState;
        this.keyHandler = keyHandler;
        this.fps = 0;
        this.lastFpsTime = 0;
        this.frameCount = 0;
        this.lastUpdateTime = System.nanoTime(); 
    }
    
    public void update(double deltaTime) {
        long currentTime = System.nanoTime();
        deltaTime = (currentTime - lastUpdateTime) / 1_000_000_000.0;
        lastUpdateTime = currentTime;
        
        // Update player movement
        Player player = gameState.getPlayer();
        player.move(keyHandler.upPressed, keyHandler.downPressed, 
                   keyHandler.leftPressed, keyHandler.rightPressed, deltaTime);


        // Check door interactions
        Room currentRoom = gameState.getCurrentRoom();
        NPC npc = currentRoom.getNPC();

        if (npc != null && npc.isNearby(player) && keyHandler.interactPressed) {
            // Show dialog with NPC
            gameState.setLastInteraction("Interacted with: " + npc.getName());
            npc.interact();
        }

        for (Door door : currentRoom.getDoors()) {
            if (door.isNearby(player) && keyHandler.interactPressed) {
                gameState.setLastInteraction("Interacted with: " + door.getName());
                gameState.setCurrentRoomIndex(door.getTargetRoomIndex());
                player.setX(GamePanel.ORIGINAL_WIDTH / 2 - GamePanel.TILE_SIZE / 2);
                player.setY(GamePanel.ORIGINAL_HEIGHT / 2 - GamePanel.TILE_SIZE / 2);
                break;
            }
        }
    }
    
    public void updateFPS() {
        frameCount++;
        if (System.currentTimeMillis() - lastFpsTime >= 1000) {
            fps = frameCount;
            frameCount = 0;
            lastFpsTime = System.currentTimeMillis();
        }
    }
    
    public GameState getGameState() {
        return gameState;
    }
    
    public int getFPS() {
        return fps;
    }
    
    public void setLastFpsTime(long time) {
        this.lastFpsTime = time;
    }
    
    public void resetFrameCount() {
        this.frameCount = 0;
    }

    public KeyHandler getKeyHandler() {
        return keyHandler;
    }
}