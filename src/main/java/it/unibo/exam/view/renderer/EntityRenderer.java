package it.unibo.exam.view.renderer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import it.unibo.exam.model.entity.Player;
import it.unibo.exam.view.texture.AssetLoader;

public class EntityRenderer {
    public void renderPlayer(Graphics2D g2, Player player) {
        BufferedImage image = null;
        AssetLoader assetLoader = AssetLoader.getInstance();
        
        if (player.isMoving()) {
            switch (player.getDirection()) {
                case Player.UP: image = assetLoader.getPlayerWalkUp()[player.getSpriteNum()]; break;
                case Player.DOWN: image = assetLoader.getPlayerWalkDown()[player.getSpriteNum()]; break;
                case Player.LEFT: image = assetLoader.getPlayerWalkLeft()[player.getSpriteNum()]; break;
                case Player.RIGHT: image = assetLoader.getPlayerWalkRight()[player.getSpriteNum()]; break;
            }
        } else {
            switch (player.getDirection()) {
                case Player.UP: image = assetLoader.getPlayerIdleUp(); break;
                case Player.DOWN: image = assetLoader.getPlayerIdleDown(); break;
                case Player.LEFT: image = assetLoader.getPlayerIdleLeft(); break;
                case Player.RIGHT: image = assetLoader.getPlayerIdleRight(); break;
            }
        }
        
        if (image != null) {
            g2.drawImage(image, player.getX(), player.getY(), player.getWidth(), player.getHeight(), null);
        } else {
            switch (player.getDirection()) {
                case Player.UP: g2.setColor(Color.CYAN); break;
                case Player.DOWN: g2.setColor(Color.BLUE); break;
                case Player.LEFT: g2.setColor(Color.GREEN); break;
                case Player.RIGHT: g2.setColor(Color.YELLOW); break;
                default: g2.setColor(Color.MAGENTA);
            }
            
            g2.fillRect(player.getX(), player.getY(), player.getWidth(), player.getHeight());
            
            g2.setColor(Color.BLACK);
            int centerX = player.getX() + player.getWidth() / 2;
            int centerY = player.getY() + player.getHeight() / 2;
            int indicatorSize = player.getWidth() / 4;
            
            switch (player.getDirection()) {
                case Player.UP: g2.fillRect(centerX - indicatorSize / 2, player.getY() + indicatorSize, indicatorSize, indicatorSize); break;
                case Player.DOWN: g2.fillRect(centerX - indicatorSize / 2, player.getY() + player.getHeight() - indicatorSize * 2, indicatorSize, indicatorSize); break;
                case Player.LEFT: g2.fillRect(player.getX() + indicatorSize, centerY - indicatorSize / 2, indicatorSize, indicatorSize); break;
                case Player.RIGHT: g2.fillRect(player.getX() + player.getWidth() - indicatorSize * 2, centerY - indicatorSize / 2, indicatorSize, indicatorSize); break;
            }
        }
    }
}