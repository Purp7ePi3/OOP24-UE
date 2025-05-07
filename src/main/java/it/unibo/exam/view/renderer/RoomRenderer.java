package it.unibo.exam.view.renderer;

import java.awt.Color;
import java.awt.Graphics2D;
import it.unibo.exam.model.entity.Door;
import it.unibo.exam.model.room.Room;
import it.unibo.exam.view.panel.GamePanel;

public class RoomRenderer {
    public void render(Graphics2D g2, Room room) {
        // Render room background
        g2.setColor(room.getBackgroundColor());
        g2.fillRect(0, 0, GamePanel.ORIGINAL_WIDTH, GamePanel.ORIGINAL_HEIGHT);

        // Render doors
        for (Door door : room.getDoors()) {
            g2.setColor(door.isSolved() ? Color.GREEN : Color.RED);
<<<<<<< Updated upstream
            g2.fillRect(door.getX(), door.getY(), door.getWidth(), door.getHeight());
=======
            g2.fillRect(p.x(), p.y(), d.W(), d.H());
            g2.setColor(Color.BLACK); // Add border
            g2.drawRect(p.x(), p.y(), d.W(), d.H());
>>>>>>> Stashed changes
        }
    }
}