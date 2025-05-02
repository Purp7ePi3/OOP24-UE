package it.unibo.exam.model.room.roomUtilities;

/**
 * RoomFlags class to manage the state of a room.
 * It contains flags to indicate if the room is solved or locked.
 */

public class RoomFlags {
    private boolean isSolved;
    private boolean isLocked;

    /**
     * Constructor to initialize the room flags.
     * By default, the room is not solved and not locked.
     */

    public RoomFlags() {
        this.isSolved = false;
        this.isLocked = false;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public void setSolved(boolean solved) {
        this.isSolved = solved;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void Lock() {
        this.isLocked = true;
    }
}