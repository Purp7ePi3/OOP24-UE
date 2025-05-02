package it.unibo.exam.view.renderer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import it.unibo.exam.Utility.Dimension;
import it.unibo.exam.Utility.Position;
import it.unibo.exam.model.entity.NPC;
import it.unibo.exam.model.entity.Player;
import it.unibo.exam.view.texture.AssetLoader;

public class EntityRenderer {
    public void renderPlayer(Graphics2D g2, Player player) {
        BufferedImage image = null;
        AssetLoader assetLoader = AssetLoader.getInstance();
        Position playerPos = player.getPosition();
        Dimension playerSize = player.getSize();
        
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
            g2.drawImage(image, playerPos.x(), playerPos.y(), playerSize.W(), playerSize.H(), null);
        } else {
            switch (player.getDirection()) {
                case Player.UP: g2.setColor(Color.CYAN); break;
                case Player.DOWN: g2.setColor(Color.BLUE); break;
                case Player.LEFT: g2.setColor(Color.GREEN); break;
                case Player.RIGHT: g2.setColor(Color.YELLOW); break;
                default: g2.setColor(Color.MAGENTA);
            }
            g2.fillRect(playerPos.x(), playerPos.y(), playerSize.W(), playerSize.H());

            
            g2.setColor(Color.BLACK);
            Position center = new Position(playerPos.x() + playerSize.W() / 2, playerPos.y() + playerSize.H() / 2);
       
            int indicatorSize = playerSize.W() / 4;
            
            switch (player.getDirection()) {
                case Player.UP: 
                    g2.fillRect(center.x() - indicatorSize / 2, playerPos.y() + indicatorSize, indicatorSize, indicatorSize); 
                    break;
                case Player.DOWN: 
                    g2.fillRect(center.x() - indicatorSize / 2, playerPos.y() + playerSize.H() - indicatorSize * 2, indicatorSize, indicatorSize); 
                    break;
                case Player.LEFT: 
                    g2.fillRect(playerPos.x() + indicatorSize, center.y() - indicatorSize / 2, indicatorSize, indicatorSize); 
                    break;
                default: // Player.RIGHT
                    g2.fillRect(playerPos.x() + playerSize.W() - indicatorSize * 2, center.y() - indicatorSize / 2, indicatorSize, indicatorSize); 
            }
        }
    }

    public void renderNPC(Graphics2D g2, NPC npc) {
        Position npcPos = npc.getPosition();
        Dimension npcSize = npc.getSize();
        g2.setColor(Color.ORANGE);
        g2.fillRect(npcPos.x(), npcPos.y(), npcSize.W(), npcSize.H());
    }
    
}