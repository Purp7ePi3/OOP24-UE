package it.unibo.exam.model.room;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import it.unibo.exam.controller.input.KeyHandler;
import it.unibo.exam.model.entity.BallEntity;
import it.unibo.exam.model.entity.BottleEntity;
import it.unibo.exam.model.entity.Door;
import it.unibo.exam.model.game.GameState;
import it.unibo.exam.view.panel.GamePanel;

public class PuzzleRoom5 extends Room implements PuzzleRoom {
    private boolean puzzleSolved = false;
    private boolean miniGameActive = false;

    private final GameState gameState;
    private BottleEntity bottle;
    private final List<BallEntity> balls = new ArrayList<>();

    private int score = 0;
    private int targetScore = 5;
    private int ballSpawnTimer = 0;
    private int loseball = 0;

    public PuzzleRoom5(final List<Door> doors, final GameState gameState) {
        super(Color.GRAY, doors);
        this.gameState = gameState;
    }
    
    @Override
    public void updatePuzzleLogic(final KeyHandler keyHandler) {
        int playerX = gameState.getPlayer().getX();
        int playerY = gameState.getPlayer().getY();
        
        if (!getDoors().isEmpty()) {
            //Door firstDoor = getDoors().get(0);
            
            if (playerX == 0 && playerY == 0 && keyHandler.interactPressed && puzzleSolved == false) {
                miniGameActive = true;
                gameState.getPlayer().setMovingEnabler(false);
                score = 0;
                balls.clear();
                bottle = new BottleEntity(
                GamePanel.ORIGINAL_WIDTH / 2 - 20,
                GamePanel.ORIGINAL_HEIGHT - 30,
                100,
                20);
            }
            if (miniGameActive) {
                // Movimento bottiglia
                if (keyHandler.leftPressed) {
                    bottle.moveLeft();
                }
                if (keyHandler.rightPressed) {
                    bottle.moveRight(GamePanel.ORIGINAL_WIDTH);
                }
    
                // Aggiorna palline
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
                            if (!getDoors().isEmpty()) {
                                Door firstDoor = getDoors().get(0);
                                firstDoor.setSolved(true);
                                gameState.updateDoorState(5, true);
                                setBackgroundColor(Color.PINK);
                            }
                        }
                    } else if (ball.isOffScreen(GamePanel.ORIGINAL_HEIGHT)) {
                        if(loseball == 2){
                            puzzleSolved = false;
                            miniGameActive = false;
                            gameState.getPlayer().setMovingEnabler(true);
                        }
                        loseball++;
                        it.remove();
                    }
                }

                // Spawna nuove palline ogni 60 frame (~1 secondo)
                ballSpawnTimer++;
                if (ballSpawnTimer > 60) {
                    ballSpawnTimer = 0;
                    int x = (int)(Math.random() * ((GamePanel.ORIGINAL_WIDTH - 200)- 200 + 1))+ 200; //min=200, max=width-200=800
                    if(x == 0){
                        balls.add(new BallEntity(x, 80));
                    }
                    balls.add(new BallEntity(x, 30));
                }

            }
        }
    }

    
    @Override
    public void draw(final Graphics2D g2) {
        g2.setColor(getBackgroundColor());
        g2.fillRect(0, 0, GamePanel.ORIGINAL_WIDTH, GamePanel.ORIGINAL_HEIGHT);
        
        // Draw doors
        for (Door door : getDoors()) {
            g2.setColor(door.isSolved() ? Color.GREEN : Color.RED);
            g2.fillRect(door.getX(), door.getY(), door.getWidth(), door.getHeight());
        }
        
        // Minigioco attivo
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
