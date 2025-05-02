package it.unibo.exam.model.game;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import it.unibo.exam.model.entity.Door;
import it.unibo.exam.model.room.Room;
import it.unibo.exam.model.room.PuzzleRoom1;
import it.unibo.exam.model.room.TheBar.PuzzleRoom2;
import it.unibo.exam.model.room.PuzzleRoom4;
import it.unibo.exam.model.room.PuzzleRoom5;
import it.unibo.exam.model.entity.NPC;

public class RoomsFactory {
    private DoorsFactory doorsFactory = new DoorsFactory();

    public static final Map<Integer, String> names = Map.of(
        0, "Main Room",
        1, "Palestra",
        2, "Bar",
        3, "Laboratorio",
        4, "Aula 2.12"
    );

    public Room createRoom(int id, GameState gameState) {
        if (gameState == null) {
            throw new IllegalArgumentException("GameState cannot be null");
        }

        // Doors always lead back to the Main Room
        List<Door> doors = List.of(
            doorsFactory.createDoor(id, 0, Color.GREEN, names.get(0))
        );

        switch (id) {
            case 0:
                // Main room — connects out to all others
                return new Room(
                    names.get(0),
                    0,
                    Color.BLUE,
                    List.of(
                        doorsFactory.createDoor(0, 1, Color.BLUE, names.get(1)),
                        doorsFactory.createDoor(0, 2, Color.BLUE, names.get(2)),
                        doorsFactory.createDoor(0, 3, Color.BLUE, names.get(3)),
                        doorsFactory.createDoor(0, 4, Color.BLUE, names.get(4))
                    ),
                    new NPC("MainRoom", "Hello, welcome to the game!", null),
                    gameState
                );

            case 1:
                // Gym puzzle
                return new PuzzleRoom1(doors, gameState);

            case 2:
                // Bar puzzle
                return new PuzzleRoom2(doors, gameState);

            case 3:
                // Lab puzzle
                return new PuzzleRoom4(doors, gameState);

            case 4:
                // Aula 2.12 puzzle
                return new PuzzleRoom5(doors, gameState);

            default:
                throw new IllegalArgumentException("Invalid room ID: " + id);
        }
    }
}
