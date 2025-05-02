package it.unibo.exam.model.game;

import java.awt.Color;

import it.unibo.exam.Utility.Position;

import it.unibo.exam.model.entity.Door;
import it.unibo.exam.view.panel.GamePanel;

public class DoorsFactory {

    public DoorsFactory() {}

    /**
     * DoorFactory is a class that creates doors for the game.
     * It is used to create doors that connect different rooms in the game.
     * The doors are created based on the room index from which they are created
     * and the room index to which they lead.
     * The doors are created with a specific color and name.
     * The doors are created with a specific position based on the room index.
     * The doors are created with a specific size based on the tile size of the game.
     */

    /**
     * Notes: Da cambiare per rendere resizable
     */

    private int maxW = GamePanel.ORIGINAL_WIDTH - GamePanel.TILE_SIZE;
    private int midW = GamePanel.ORIGINAL_WIDTH / 2 - GamePanel.TILE_SIZE / 2;
    private int midH = GamePanel.ORIGINAL_HEIGHT / 2 - GamePanel.TILE_SIZE / 2;
    private int maxH = GamePanel.ORIGINAL_HEIGHT - GamePanel.TILE_SIZE;
    private int minW = 0;
    private int minH = 0;

    private Position left = new Position(minW, midH);
    private Position up = new Position(midW, minH);
    private Position right = new Position(maxW, midH);
    private Position down = new Position(midW, maxH);

    /**
     * Creates a door object based on the given parameters.
     * - From: the room index from which the door is created
     * - To: the room index to which the door leads
     * - Color: the color of the door
     * - Name: the name of the door
     */
    public Door createDoor(int from, int to, Color color, String name) {
        /**
        * Door position based on the room index
        * 0 -> Main Room
        * 1 -> Palestra
        * 2 -> Bar
        * 3 -> Laboratorio
        * 4 -> Aula 2.12
        */
        Position position = null;

        if (from == 0) {
            switch (to) {
                case 1:
                    position = left;
                    break;
                case 2:
                    position = up;
                    break;
                case 3:
                    position = right;
                    break;
                default:
                    position = down;
                    break;
            }
        } else {
            switch (from) {
                case 1:
                    position = right;
                    break;
                case 2:
                    position = down;
                    break;
                case 3:
                    position = left;
                    break;
                default:
                    position = up;
                    break;
            }   
        }

        return new Door(position, name, to, false);

    }
    
}
