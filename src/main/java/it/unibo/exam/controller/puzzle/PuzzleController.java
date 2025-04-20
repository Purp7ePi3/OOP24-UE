package it.unibo.exam.controller.puzzle;

import it.unibo.exam.controller.input.KeyHandler;
import it.unibo.exam.model.entity.Player;
import it.unibo.exam.model.game.GameState;
import it.unibo.exam.model.room.PuzzleRoom;
import it.unibo.exam.model.room.Room;
import it.unibo.exam.view.panel.MainMenuPanel;

@SuppressWarnings("unused")
public class puzzleController {
    private final GameState gameState;
    private final KeyHandler keyHandler;
    
    private static final String[] ROOM_NAMES = {
        "Palestra", "Bar", "Laboratorio", "Aula 2.12", "Giardino"
    };

    public puzzleController(GameState gameState, KeyHandler keyHandler) {
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
        }
    }

    private boolean isPuzzleAlreadyProcessed(PuzzleRoom room) {
        // This could check a flag or list in GameState to prevent re-processing solved puzzles
        // For now, we'll rely on the room's own state
        return false; // Simplified: assume we process each solve event once
    }
    
    private void handlePuzzleSolved(PuzzleRoom room) {
        // Update the game state based on which puzzle room was solved
        int roomIndex = gameState.getCurrentRoomIndex();
        gameState.updateDoorState(roomIndex, true);
        String roomName = (roomIndex >= 0 && roomIndex < ROOM_NAMES.length + 1) 
            ? ROOM_NAMES[roomIndex - 1] 
            : "Unknown Room";
    
        gameState.setLastInteraction(roomName + " solved!");
    }

    // Getter if needed elsewhere
    public GameState getGameState() {
        return gameState;
    }
}

