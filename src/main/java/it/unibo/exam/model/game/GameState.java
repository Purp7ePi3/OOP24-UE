package it.unibo.exam.model.game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import it.unibo.exam.model.entity.Door;
import it.unibo.exam.model.entity.Player;
import it.unibo.exam.model.room.Room;
import it.unibo.exam.model.room.*;
import it.unibo.exam.view.panel.GamePanel;

public class GameState {
    private List<Room> rooms;
    private int currentRoomIndex;
    private Player player;
    private String lastInteraction = "";
    
    public GameState() {
        rooms = new ArrayList<>();
        currentRoomIndex = 0;
        
        player = new Player(GamePanel.ORIGINAL_WIDTH / 2 - GamePanel.TILE_SIZE / 2,
                          GamePanel.ORIGINAL_HEIGHT / 2 - GamePanel.TILE_SIZE / 2,
                          20);
        
        createRooms();
    }
    
    private void createRooms() {
        List<Door> room1Doors = List.of(
            new Door(0, GamePanel.ORIGINAL_HEIGHT / 4 - GamePanel.TILE_SIZE / 2, "Palestra", 1, false),
            new Door(0, 3 * GamePanel.ORIGINAL_HEIGHT / 4 - GamePanel.TILE_SIZE / 2, "Bar", 2, false),
            new Door(GamePanel.ORIGINAL_WIDTH - GamePanel.TILE_SIZE, GamePanel.ORIGINAL_HEIGHT / 4 - GamePanel.TILE_SIZE / 2, "Laboratorio", 3, false),
            new Door(GamePanel.ORIGINAL_WIDTH - GamePanel.TILE_SIZE, 3 * GamePanel.ORIGINAL_HEIGHT / 4 - GamePanel.TILE_SIZE / 2, "Aula 2.12", 4, false),
            new Door(GamePanel.ORIGINAL_WIDTH / 2 - GamePanel.TILE_SIZE / 2, GamePanel.ORIGINAL_HEIGHT - GamePanel.TILE_SIZE, "Giarino", 5, false));
        rooms.add(new Room(Color.GRAY, room1Doors));
        
        rooms.add(new PuzzleRoom1(List.of(
            new Door(GamePanel.ORIGINAL_WIDTH - GamePanel.TILE_SIZE, GamePanel.ORIGINAL_HEIGHT - GamePanel.TILE_SIZE, "Back to Main", 0, false)), this));
        rooms.add(new PuzzleRoom2(List.of(
            new Door(GamePanel.ORIGINAL_WIDTH - GamePanel.TILE_SIZE, GamePanel.ORIGINAL_HEIGHT - GamePanel.TILE_SIZE, "Back to Main", 0, false)), this));
        rooms.add(new PuzzleRoom3(List.of(
            new Door(GamePanel.ORIGINAL_WIDTH - GamePanel.TILE_SIZE, GamePanel.ORIGINAL_HEIGHT - GamePanel.TILE_SIZE, "Back to Main", 0, false)), this));
        rooms.add(new PuzzleRoom4(List.of(
            new Door(GamePanel.ORIGINAL_WIDTH - GamePanel.TILE_SIZE, GamePanel.ORIGINAL_HEIGHT - GamePanel.TILE_SIZE, "Back to Main", 0, false)), this));
        rooms.add(new PuzzleRoom4(List.of(
            new Door(GamePanel.ORIGINAL_WIDTH - GamePanel.TILE_SIZE, GamePanel.ORIGINAL_HEIGHT - GamePanel.TILE_SIZE, "Back to Main", 0, false)), this));
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
