package it.unibo.exam.view.texture;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;

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


    // Track if assets were successfully loaded
    private boolean assetsLoaded = false;
    
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
            // Initialize arrays first to prevent NullPointerExceptions
            playerWalkDown = new BufferedImage[3];
            playerWalkUp = new BufferedImage[3];
            playerWalkLeft = new BufferedImage[3];
            playerWalkRight = new BufferedImage[3];
            
            // Load player idle textures
            playerIdleDown = loadImage("/it/unibo/exam/view/texture/player/player_walk_front_1.png");
            playerIdleUp = loadImage("/it/unibo/exam/view/texture/player/player_walk_back_1.png");
            playerIdleLeft = loadImage("/it/unibo/exam/view/texture/player/player_walk_left_1.png");
            playerIdleRight = loadImage("/it/unibo/exam/view/texture/player/player_walk_right_1.png");

            
            for (int i = 0; i < 3; i++) {
                playerWalkDown[i] = loadImage("/it/unibo/exam/view/texture/player/player_walk_front_" + i + ".png");
                playerWalkUp[i] = loadImage("/it/unibo/exam/view/texture/player/player_walk_back_" + i + ".png");
                playerWalkLeft[i] = loadImage("/it/unibo/exam/view/texture/player/player_walk_left_" + i + ".png");
                playerWalkRight[i] = loadImage("/it/unibo/exam/view/texture/player/player_walk_right_" + i + ".png");
            }
            
            assetsLoaded = true;
            System.out.println("Game assets loaded successfully!");
        } catch (Exception e) {
            System.err.println("Error loading assets: " + e.getMessage());
            System.err.println("Using default player rendering instead of textures");
        }
    }

    private BufferedImage loadImage(String path) {
        try {
            BufferedImage img = ImageIO.read(getClass().getResourceAsStream(path));
            if (img == null) {
                System.err.println("Missing asset: " + path);
                // Fallback to a default image (you can create a basic placeholder image here)
                img = createDefaultImage();
            }
            return img;
        } catch (IOException e) {
            System.err.println("Error loading image from " + path + ": " + e.getMessage());
            return createDefaultImage(); // Fallback to default image
        }
    }

    private BufferedImage createDefaultImage() {
        // You can create a simple placeholder image like a solid color rectangle
        BufferedImage defaultImage = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < 32; x++) {
            for (int y = 0; y < 32; y++) {
                defaultImage.setRGB(x, y, Color.GRAY.getRGB()); // Use any fallback color
            }
        }
        return defaultImage;
    }

    public boolean areAssetsLoaded() {
        return assetsLoaded;
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
