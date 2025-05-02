package it.unibo.exam.model.room;

import it.unibo.exam.controller.input.KeyHandler;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;

import it.unibo.exam.model.room.roomUtilities.RoomID;
import it.unibo.exam.model.room.roomUtilities.RoomFlags;
import it.unibo.exam.Utility.Dimension;
import it.unibo.exam.Utility.Position;
import it.unibo.exam.model.entity.Door;
import it.unibo.exam.model.entity.NPC;
import it.unibo.exam.model.game.GameState;
import it.unibo.exam.view.panel.GamePanel;

public class Room implements PuzzleRoom {

    @SuppressWarnings("unused")
    private static final long serialVersionUID = 1L;
    @SuppressWarnings("unused")
    private RoomID id;
    private Color backgroundColor;
    private List<Door> doors;
    private NPC npc;
    private RoomFlags flags = new RoomFlags();
    protected final GameState gameState;

    // ✅ Full constructor (unchanged)
    public Room(String name, int index, Color backgroundColor, List<Door> doors, NPC npc, GameState gameState) {
        this.id = new RoomID(index, name);
        this.backgroundColor = backgroundColor;
        this.doors = doors;
        this.npc = npc;
        this.gameState = gameState;
    }

    // ✅ New lightweight constructor for PuzzleRoom2
    public Room(Color backgroundColor, List<Door> doors, GameState gameState) {
        this.backgroundColor = backgroundColor;
        this.doors = doors;
        this.gameState = gameState;
        this.flags = new RoomFlags();
    }

    public List<Door> getDoors() {
        return doors;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
    }

    @Override
    public void updatePuzzleLogic(final KeyHandler keyHandler) {
        Position playerPosition = gameState.getPlayer().getPosition();

        if (!getDoors().isEmpty()) {
            Door firstDoor = getDoors().get(0);

            if (playerPosition.x() == 0 && playerPosition.y() == 0 && keyHandler.interactPressed) {
                this.flags.setSolved(true);
                firstDoor.setSolved(true);
                // Update the door in the main room
                gameState.updateDoorState(1, true); // 1 is the index of this puzzle room
                setBackgroundColor(Color.PINK);
            }
        }
    }

    @Override
    public void draw(final Graphics2D g2) {
        g2.setColor(getBackgroundColor());
        g2.fillRect(0, 0, GamePanel.ORIGINAL_WIDTH, GamePanel.ORIGINAL_HEIGHT);

        for (Door door : getDoors()) {
            Position doorPosition = door.getPosition();
            Dimension doorSize = door.getSize();
            g2.setColor(door.isSolved() ? Color.GREEN : Color.RED);
            g2.fillRect(doorPosition.x(), doorPosition.y(), doorSize.W(), doorSize.H());
        }

        if (isPuzzleSolved()) {
            g2.setColor(Color.WHITE);
            g2.setFont(g2.getFont().deriveFont(24f));
            g2.drawString("Puzzle Solved!", GamePanel.ORIGINAL_WIDTH / 2 - 80, GamePanel.ORIGINAL_HEIGHT / 2);
        }
    }

    @Override
    public boolean isPuzzleSolved() {
        return this.flags.isSolved();
    }

    public NPC getNPC() {
        return npc;
    }
}
