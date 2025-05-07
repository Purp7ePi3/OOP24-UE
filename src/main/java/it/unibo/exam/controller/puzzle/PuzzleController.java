package it.unibo.exam.controller.puzzle;

import java.awt.Graphics2D;

import it.unibo.exam.controller.input.KeyHandler;
import it.unibo.exam.model.entity.Minigame;
import it.unibo.exam.model.entity.Player;
import it.unibo.exam.model.game.GameState;
import it.unibo.exam.model.room.PuzzleRoom;

@SuppressWarnings("unused")
public class PuzzleController {
    private final GameState gameState;
    private final KeyHandler keyHandler;
<<<<<<< Updated upstream
    
    private static final String[] ROOM_NAMES = {
        "Palestra", "Bar", "Laboratorio", "Aula 2.12", "Giardino"
    };
=======
>>>>>>> Stashed changes

    public PuzzleController(GameState gameState, KeyHandler keyHandler) {
        this.gameState = gameState;
        this.keyHandler = keyHandler;
    }

    public void update(double deltaTime) {
        // Only update if current room is a PuzzleRoom
        if (gameState.getCurrentRoom() instanceof PuzzleRoom) {
            PuzzleRoom currentPuzzleRoom = (PuzzleRoom) gameState.getCurrentRoom();
            Player player = gameState.getPlayer();

            // Delegate puzzle logic to the room
            currentPuzzleRoom.updatePuzzleLogic(keyHandler);

            // Check if puzzle is solved and update game state accordingly
            if (currentPuzzleRoom.isPuzzleSolved() && !isPuzzleAlreadyProcessed(currentPuzzleRoom)) {
                handlePuzzleSolved(currentPuzzleRoom);
            }

            // Handle minigame activation and updates
            Minigame minigame = currentPuzzleRoom.getMinigame();

            if (minigame != null) {
                if (!minigame.isActive() && keyHandler.interactPressed) {
                    minigame.setActive(true); // Activate the minigame
                }
                if (minigame.isActive()) {
                    minigame.update(keyHandler, gameState); // Update the minigame logic
                }
            }
        }
    }

    private boolean isPuzzleAlreadyProcessed(PuzzleRoom room) {
        // This could check a flag or list in GameState to prevent re-processing solved
        // puzzles
        // For now, we'll rely on the room's own state
        return false; // Simplified: assume we process each solve event once
    }

    private void handlePuzzleSolved(PuzzleRoom room) {
        // Update the game state based on which puzzle room was solved
        int roomIndex = gameState.getCurrentRoomIndex();
        gameState.updateDoorState(roomIndex, true);
<<<<<<< Updated upstream
        String roomName = (roomIndex >= 0 && roomIndex < ROOM_NAMES.length + 1) 
            ? ROOM_NAMES[roomIndex - 1] 
            : "Unknown Room";
    
=======
        String roomName = RoomsFactory.names().get(roomIndex);

>>>>>>> Stashed changes
        gameState.setLastInteraction(roomName + " solved!");
    }

    // Getter if needed elsewhere
    public GameState getGameState() {
        return gameState;
    }

    public void renderMinigame(Graphics2D g2, Minigame minigame) {
        // Render the puzzle room and its elements
        if (minigame != null && minigame.isActive()) {
            minigame.drawGame(g2); // Draw the minigame if active
        }
    }    
}
