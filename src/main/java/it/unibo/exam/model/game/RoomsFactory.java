package it.unibo.exam.model.game;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import it.unibo.exam.model.entity.Door;
import it.unibo.exam.model.entity.Minigame;
import it.unibo.exam.model.entity.Minigame4Logic;
import it.unibo.exam.model.entity.NPC;
import it.unibo.exam.model.room.Room;


/**
 * Factory class for creating Room objects.
 * This class is responsible for creating rooms with specific properties.
 * It uses the DoorsFactory to create doors for the rooms.
 * The rooms are identified by an ID, and each room has a name, color, doors, minigame and an NPC.
 */
public class RoomsFactory {
    private DoorsFactory doorsFactory = new DoorsFactory();

    private static Map<Integer, String> names = Map.of( 
        0, "Main Room",
        1, "Palestra",
        2, "Bar",
        3, "Laboratorio",
        4, "Aula 2.12"
    );

    public static Map<Integer,String> names() {
        return names;
    }

    /**
     * Creates a room object based on the given parameters.
     * - Color: the color of the room
     * - Name: the name of the room
     * - GameState: the game state
     */

    public Room createRoom(int id, GameState gameState) {

        Color color = null;
        List<Door> doors = null;
        NPC npc = null;
        String name = null;
        Minigame minigame = null;
        
        if (id < 0 || id > 4) {
            throw new IllegalArgumentException("Invalid room ID");
        }

        if (gameState == null) {
            throw new IllegalArgumentException("Color and gamestate cannot be null");
        }

        switch (id) {
            case 0:
                name = names.get(id);
                color = Color.BLUE;
                doors = List.of(
                    doorsFactory.createDoor(id, 1, color, "Palestra"),
                    doorsFactory.createDoor(id, 2, color, "Bar"),
                    doorsFactory.createDoor(id, 3, color, "Laboratorio"),
                    doorsFactory.createDoor(id, 4, color, "Aula 2.12")
                );
                npc = new NPC("MainRoom", "Hello, welcome to the game!");
                break;
            case 1:
            case 2:
            case 3:
            case 4:
                name = names.get(id);
                color = Color.RED; // Ensure color is initialized before being used
                npc = new NPC("NPC" + name, "I have a puzzle for you!");
                doors = List.of(
                    doorsFactory.createDoor(id, 0, color, "Main Room")
                );
                if (id == 4) {
                    minigame = new Minigame4Logic(gameState);
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid room ID");
        }

        if (name == null || color == null || doors == null) {
            throw new IllegalArgumentException("Room properties cannot be null");
        }

        System.out.println("Creating room: " + name);
        System.out.println("Doors: " + doors);
        System.out.println("NPC: " + npc);
        System.out.println("GameState: " + gameState);
        System.out.println("Minigame: " + minigame);

        return new Room(name, id, color, doors, npc, gameState, minigame);
    }
}
