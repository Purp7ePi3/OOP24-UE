package it.unibo.exam.model.room;

import java.awt.Color;
import java.util.List;
<<<<<<< Updated upstream
import it.unibo.exam.model.entity.Door;

public class Room {
    private Color backgroundColor;
    private List<Door> doors;
    
    public Room(Color backgroundColor, List<Door> doors) {
        this.backgroundColor = backgroundColor;
        this.doors = doors;
    }
    
    public List<Door> getDoors() {
        return doors;
=======

import it.unibo.exam.Utility.Dimension;
import it.unibo.exam.Utility.Position;
import it.unibo.exam.controller.input.KeyHandler;
import it.unibo.exam.model.entity.Door;
import it.unibo.exam.model.entity.Minigame;
import it.unibo.exam.model.entity.NPC;
import it.unibo.exam.model.game.GameState;
import it.unibo.exam.model.room.roomUtilities.RoomID;
import it.unibo.exam.view.panel.GamePanel;

public class Room implements PuzzleRoom {

    // private static final long serialVersionUID = 1L;
    private RoomID id;
    private Color backgroundColor;
    private List<Door> doors;
    private NPC npc;
    // private RoomFlags flags = new RoomFlags();
    private final GameState gameState;
    private Minigame minigame;   
    
    public Room(String name, int index, Color backgroundColor, List<Door> doors, NPC npc, GameState gameState, Minigame minigame) {
        this.id = new RoomID(index, name);
        this.gameState = gameState;
        this.backgroundColor = backgroundColor;
        this.doors = doors;
        this.npc = npc;
        this.minigame = minigame;
>>>>>>> Stashed changes
    }
    
    public Color getBackgroundColor() {
        return backgroundColor;
    }
    
    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
    }
<<<<<<< Updated upstream
=======


    /**
     * * Updates the logic of the puzzle in the room
     * @param keyHandler the key handler to process input
     */
    public void updatePuzzleLogic(final KeyHandler keyHandler) {
        if (minigame != null) {
            minigame.update(keyHandler, gameState);
        } else { // If no minigame, check for door interactions
            Position playerPos = gameState.getPlayer().getPos();
            int playerX = playerPos.x();
            int playerY = playerPos.y();

            for (Door door : getDoors()) {
                Position doorPos = door.getPosition();
                Dimension doorSize = door.getSize();

                // Check if the player is near the door and interacts
                if (playerX >= doorPos.x() && playerX <= doorPos.x() + doorSize.W() &&
                    playerY >= doorPos.y() && playerY <= doorPos.y() + doorSize.H() &&
                    keyHandler.interactPressed) {
                    
                    door.setSolved(true);
                    if (minigame != null) {
                        minigame.setSolved(true);
                    }
                    // Update the door state in the game
                    gameState.updateDoorState(id.id(), true);
                    // Change room color to indicate puzzle solved
                    setBackgroundColor(Color.BLUE);
                }
            }
        }
    }
    
    /**
     * * Draws the room and its elements
     * @param g2 the graphics object to draw on
     */
    @Override
    public void draw(final Graphics2D g2) {
        // Draw room background
        g2.setColor(getBackgroundColor());
        g2.fillRect(0, 0, GamePanel.ORIGINAL_WIDTH, GamePanel.ORIGINAL_HEIGHT);

        // Draw doors
        drawDoors(g2);

        // Draw minigame if present
        if (minigame != null) {
            minigame.drawGame(g2);
        }

        // Indicate if the puzzle is solved
        if (isPuzzleSolved()) {
            g2.setColor(Color.WHITE);
            g2.setFont(g2.getFont().deriveFont(24f));
            g2.drawString("Puzzle Solved!", GamePanel.ORIGINAL_WIDTH / 2 - 80, GamePanel.ORIGINAL_HEIGHT / 2);
        }
    }


    /**
     * Draws the doors of the room
     * @param g2 the graphics object to draw on
     */
    private void drawDoors(final Graphics2D g2) {
        for (Door door : getDoors()) {
            Position doorPosition = door.getPosition();
            Dimension doorSize = door.getSize();
            g2.setColor(door.isSolved() ? Color.GREEN : Color.RED);
            g2.fillRect(doorPosition.x(),doorPosition.y() , doorSize.W(), doorSize.H());
        }
    }


    /**
     * @return @TRUE if the puzzle is solved, @FALSE otherwise 
     */
    
    @Override
    public boolean isPuzzleSolved() {
        if (minigame != null) {
            return minigame.isSolved();
        }
        return false;
    }

    /**
     * * @return @TRUE if the minigame exist and is active, @FALSE otherwise
     */
    public boolean isMinigameActive() {
        return minigame != null && minigame.isActive();
    }

    /**
     * @return the NPC of the room
     */
    public NPC getNPC(){
        return npc;
    }


    /**
     * * @return the list of the doors in the room
     */
    public List<Door> getDoors() {
        return doors;
    }

    public RoomID getRoomID() {
        return id;
    }

    @Override
    public Minigame getMinigame() {
        return minigame; // Return the minigame instance
    }

>>>>>>> Stashed changes
}