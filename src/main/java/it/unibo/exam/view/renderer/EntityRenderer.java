package main.java.it.unibo.exam.view.renderer;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import main.java.it.unibo.exam.model.entity.Player;
import main.java.it.unibo.exam.view.asset.AssetLoader;

public class EntityRenderer {
    public void renderPlayer(Graphics2D g2, Player player) {
        BufferedImage image = null;
        AssetLoader assetLoader = AssetLoader.getInstance();
        
        // Determine which image to use based on player state
        if (player.isMoving()) {
            // Animation for movement
            switch (player.getDirection()) {
                case Player.UP:
                    image = assetLoader.getPlayerWalkUp()[player.getSpriteNum()];
                    break;
                case Player.DOWN:
                    image = assetLoader.getPlayerWalkDown()[player.getSpriteNum()];
                    break;
                case Player.LEFT:
                    image = assetLoader.getPlayerWalkLeft()[player.getSpriteNum()];
                    break;
                case Player.RIGHT:
                    image = assetLoader.getPlayerWalkRight()[player.getSpriteNum()];
                    break;
            }
        } else {
            // Static image for idle state
            switch (player.getDirection()) {
                case Player.UP:
                    image = assetLoader.getPlayerIdleUp();
                    break;
                case Player.DOWN:
                    image = assetLoader.getPlayerIdleDown();
                    break;
                case Player.LEFT:
                    image = assetLoader.getPlayerIdleLeft();
                    break;
                case Player.RIGHT:
                    image = assetLoader.getPlayerIdleRight();
                    break;
            }
        }
        
        // Draw the image if available, or fall back to rectangle if image is null
        if (image != null) {
            g2.drawImage(image, player.getX(), player.getY(), player.getWidth(), player.getHeight(), null);
        } else {
            // Fallback if image is not loaded
            g2.fillRect(player.getX(), player.getY(), player.getWidth(), player.getHeight());
        }
    }
}