package it.unibo.exam.model.game;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import it.unibo.exam.model.entity.Door;
import it.unibo.exam.model.entity.NPC;
import it.unibo.exam.model.room.Room;

public class RoomsFactory {
    private DoorsFactory doorsFactory = new DoorsFactory();

    public static  Map<Integer, String> names = Map.of( 
        0, "Main Room",
        1, "Palestra",
        2, "Bar",
        3, "Laboratorio",
        4, "Aula 2.12"
    );

    /**
     * Creates a room object based on the given parameters.
     * - Color: the color of the room
     * - Name: the name of the room
     * - GameState: the game state
     */

    public  Room createRoom(int id, GameState gameState) {
        Color color = null;
        List<Door> doors = null;
        NPC npc = null;
        String name = null;

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
                npc = new NPC("MainRoom", "Hello, welcome to the game!", null);
                break;
            case 1:
            case 2:
            case 3:
            case 4:
                name = names.get(id);
                color = Color.GREEN;
                npc = new NPC("NPC" + id, "I have a puzzle for you!", null);
                doors = List.of(
                    doorsFactory.createDoor(id, 0, color, "Main Room")
                );
                break;
            default:
                throw new IllegalArgumentException("Invalid room ID");
        }

        return new Room(name, id, color, doors, npc, gameState);
    }
}
