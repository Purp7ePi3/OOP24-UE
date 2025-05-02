package it.unibo.exam.model.room;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import it.unibo.exam.Utility.Dimension;
import it.unibo.exam.Utility.Position;
import it.unibo.exam.controller.input.KeyHandler;
import it.unibo.exam.model.entity.BallEntity;
import it.unibo.exam.model.entity.BottleEntity;
import it.unibo.exam.model.entity.Door;
import it.unibo.exam.model.game.GameState;
import it.unibo.exam.view.panel.GamePanel;

@SuppressWarnings("unused")
public class PuzzleRoom5 extends Room implements PuzzleRoom {
    private boolean puzzleSolved = false;
    private boolean miniGameActive = false;

    private BottleEntity bottle;
    private final List<BallEntity> balls = new ArrayList<>();

    private int score = 0;
    private int targetScore = 5;
    private int ballSpawnTimer = 0;
    private int loseball = 0;

    public PuzzleRoom5(final List<Door> doors, final GameState gameState) {
        super(Color.GRAY, doors, gameState);
    }

    @Override
    public void updatePuzzleLogic(final KeyHandler keyHandler) {
        int playerX = gameState.getPlayer().getX();
        int playerY = gameState.getPlayer().getY();

        if (!getDoors().isEmpty()) {
            if (playerX == 0 && playerY == 0 && keyHandler.interactPressed && !puzzleSolved) {
                miniGameActive = true;
                gameState.getPlayer().setMovingEnabler(false);
                score = 0;
                loseball = 0;
                balls.clear();
                bottle = new BottleEntity(
                    "Bottle",
                    GamePanel.ORIGINAL_WIDTH / 2 - 20,
                    GamePanel.ORIGINAL_HEIGHT - 30,
                    100,
                    20
                );
            }

            if (miniGameActive) {
                // Bottle movement
                if (keyHandler.leftPressed) {
                    bottle.moveLeft();
                }
                if (keyHandler.rightPressed) {
                    bottle.moveRight(GamePanel.ORIGINAL_WIDTH);
                }

                // Update balls
                Iterator<BallEntity> it = balls.iterator();
                while (it.hasNext()) {
                    BallEntity ball = it.next();
                    ball.update();

                    if (bottle.catchBall(ball)) {
                        it.remove();
                        score++;
                        if (score >= targetScore) {
                            puzzleSolved = true;
                            miniGameActive = false;
                            gameState.getPlayer().setMovingEnabler(true);

                            Door firstDoor = getDoors().get(0);
                            firstDoor.setSolved(true);
                            gameState.updateDoorState(5, true);
                            setBackgroundColor(Color.PINK);
                        }
                    } else if (ball.isOffScreen(GamePanel.ORIGINAL_HEIGHT)) {
                        loseball++;
                        it.remove();
                        if (loseball >= 3) {
                            puzzleSolved = false;
                            miniGameActive = false;
                            gameState.getPlayer().setMovingEnabler(true);
                        }
                    }
                }

                // Spawn new ball every ~60 ticks
                ballSpawnTimer++;
                if (ballSpawnTimer > 60) {
                    ballSpawnTimer = 0;
                    int x = (int)(Math.random() * ((GamePanel.ORIGINAL_WIDTH - 200) - 200 + 1)) + 200;
                    balls.add(new BallEntity(
                        "Ball",
                        new Position(x, 0),
                        new Dimension(30, 30)
                    ));
                }
            }
        }
    }

    @Override
    public void draw(final Graphics2D g2) {
        g2.setColor(getBackgroundColor());
        g2.fillRect(0, 0, GamePanel.ORIGINAL_WIDTH, GamePanel.ORIGINAL_HEIGHT);

        for (Door door : getDoors()) {
            g2.setColor(door.isSolved() ? Color.GREEN : Color.RED);
            g2.fillRect(door.getX(), door.getY(), door.getWidth(), door.getHeight());
        }

        if (miniGameActive) {
            bottle.draw(g2);
            for (BallEntity ball : balls) {
                ball.draw(g2);
            }

            g2.setColor(Color.WHITE);
            g2.setFont(g2.getFont().deriveFont(18f));
            g2.drawString("Score: " + score, GamePanel.ORIGINAL_WIDTH / 2, 20);
        }

        if (puzzleSolved) {
            g2.setColor(Color.WHITE);
            g2.setFont(g2.getFont().deriveFont(24f));
            g2.drawString("Puzzle Solved!", GamePanel.ORIGINAL_WIDTH / 2 - 80, GamePanel.ORIGINAL_HEIGHT / 2);
        }
    }

    @Override
    public boolean isPuzzleSolved() {
        return puzzleSolved;
    }
}
