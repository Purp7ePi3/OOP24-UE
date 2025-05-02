package it.unibo.exam.model.game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import it.unibo.exam.model.entity.Door;
import it.unibo.exam.model.entity.Player;
import it.unibo.exam.model.room.Room;
import it.unibo.exam.model.room.TheBar.PuzzleRoom2;
import it.unibo.exam.model.room.*;
import it.unibo.exam.view.panel.GamePanel;


@SuppressWarnings("unused")
public class GameState {
    private List<Room> rooms;
    private int currentRoomIndex;
    private Player player;
    private String lastInteraction = "";
    private RoomsFactory roomsFactory = new RoomsFactory();
    
    public GameState() {
        rooms = new ArrayList<>();
        currentRoomIndex = 0;

        int playerX = GamePanel.ORIGINAL_WIDTH / 2 - GamePanel.TILE_SIZE / 2;
        int playerY = GamePanel.ORIGINAL_HEIGHT / 2 - GamePanel.TILE_SIZE / 2;
        player = new Player(playerX, playerY, 20);

        createRooms();
    }

    private void createRooms() {
        rooms = IntStream.rangeClosed(0, 4)
                .mapToObj(i -> roomsFactory.createRoom(i, this))
                .toList();
    }

    public Room getCurrentRoom() {
        return rooms.get(currentRoomIndex);
    }
    
    public int getCurrentRoomIndex() {
        return currentRoomIndex;
    }
    
    public void setCurrentRoomIndex(int index) {
        if (index >= 0 && index < rooms.size()) {
            currentRoomIndex = index;
        }
    } 

    public Player getPlayer() {
        return player;
    }
    
    public String getLastInteraction() {
        return lastInteraction;
    }
    
    public void setLastInteraction(String interaction) {
        this.lastInteraction = interaction;
    }
    
    public void updateDoorState(int targetRoomIndex, boolean solved) {
        Room mainRoom = rooms.get(0);
        for (Door door : mainRoom.getDoors()) {
            if (door.getTargetRoomIndex() == targetRoomIndex) {
                door.setSolved(solved);
            }
        }
    }
}
