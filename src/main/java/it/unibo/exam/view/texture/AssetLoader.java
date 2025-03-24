package it.unibo.exam.view.texture;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class AssetLoader {
    // Singleton pattern
    private static AssetLoader instance;
    
    // Player textures
    private BufferedImage playerIdleUp;
    private BufferedImage playerIdleDown;
    private BufferedImage playerIdleLeft;
    private BufferedImage playerIdleRight;
    
    private BufferedImage[] playerWalkUp;
    private BufferedImage[] playerWalkDown;
    private BufferedImage[] playerWalkLeft;
    private BufferedImage[] playerWalkRight;
    
    private AssetLoader() {
        loadAssets();
    }
    
    public static AssetLoader getInstance() {
        if (instance == null) {
            instance = new AssetLoader();
        }
        return instance;
    }
    
    private void loadAssets() {
        try {
            // Load player idle textures
            playerIdleDown = ImageIO.read(getClass().getResourceAsStream("/player/player_idle_down.png"));
            playerIdleUp = ImageIO.read(getClass().getResourceAsStream("/player/player_idle_up.png"));
            playerIdleLeft = ImageIO.read(getClass().getResourceAsStream("/player/player_idle_left.png"));
            playerIdleRight = ImageIO.read(getClass().getResourceAsStream("/player/player_idle_right.png"));
            
            // Load player walking animations
            playerWalkDown = new BufferedImage[4];
            playerWalkUp = new BufferedImage[4];
            playerWalkLeft = new BufferedImage[4];
            playerWalkRight = new BufferedImage[4];
            
            for (int i = 0; i < 4; i++) {
                playerWalkDown[i] = ImageIO.read(getClass().getResourceAsStream("/player/player_walk_down_" + i + ".png"));
                playerWalkUp[i] = ImageIO.read(getClass().getResourceAsStream("/player/player_walk_up_" + i + ".png"));
                playerWalkLeft[i] = ImageIO.read(getClass().getResourceAsStream("/player/player_walk_left_" + i + ".png"));
                playerWalkRight[i] = ImageIO.read(getClass().getResourceAsStream("/player/player_walk_right_" + i + ".png"));
            }
        } catch (IOException e) {
            System.err.println("Error loading assets: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Getters for player textures
    public BufferedImage getPlayerIdleUp() {
        return playerIdleUp;
    }
    
    public BufferedImage getPlayerIdleDown() {
        return playerIdleDown;
    }
    
    public BufferedImage getPlayerIdleLeft() {
        return playerIdleLeft;
    }
    
    public BufferedImage getPlayerIdleRight() {
        return playerIdleRight;
    }
    
    public BufferedImage[] getPlayerWalkUp() {
        return playerWalkUp;
    }
    
    public BufferedImage[] getPlayerWalkDown() {
        return playerWalkDown;
    }
    
    public BufferedImage[] getPlayerWalkLeft() {
        return playerWalkLeft;
    }
    
    public BufferedImage[] getPlayerWalkRight() {
        return playerWalkRight;
    }
}