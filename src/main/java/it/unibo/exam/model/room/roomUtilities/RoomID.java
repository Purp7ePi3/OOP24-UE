package it.unibo.exam.model.room.roomUtilities;
/**
 * RoomId class to represent the ID of a room.
 * It contains an integer ID and a string name.
 */


public record RoomID(int id, String name) {
    public RoomID(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public int getId() {
        return id;
    }
}
