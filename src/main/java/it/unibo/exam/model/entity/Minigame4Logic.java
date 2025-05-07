package it.unibo.exam.model.entity;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import it.unibo.exam.controller.input.KeyHandler;
import it.unibo.exam.model.game.GameState;
import it.unibo.exam.view.panel.GamePanel;

public class Minigame4Logic extends Minigame {
    
    private boolean isActive = false;
    private boolean isSolved = false;

    private final GameState gameState;
    private BottleEntity bottle;
    private final List<BallEntity> balls = new ArrayList<>();
    private Minigame4Gui gui;

    public Minigame4Logic(final GameState gameState) {
        this.gameState = gameState;
        this.gui = new Minigame4Gui(this);
    }

    private int score = 0;
    private int targetScore = 5;
    private int ballSpawnTimer = 0;
    private int loseball = 0;

    private void initGame() {
        isActive = true;
        isSolved = false; // Assicura che il minigioco inizi non risolto
        loseball = 0; // Resetta il contatore delle palline perse

        gameState.getPlayer().setMovingEnabler(false);

        score = 0;
        balls.clear();

        bottle = new BottleEntity(
            GamePanel.ORIGINAL_WIDTH / 2 - 50, // Centra la bottiglia
            GamePanel.ORIGINAL_HEIGHT - 50,   // Posiziona la bottiglia in basso
            100,
            20
        );
    }

    @Override
    public void update(KeyHandler keyHandler, GameState gameState) {
        Player player = gameState.getPlayer();
        NPC npc = gameState.getCurrentRoom().getNPC();

        if (!isSolved && !isActive && npc != null && npc.isNearby(player) && keyHandler.interactPressed) {
            JOptionPane.showMessageDialog(null, "Inizia il minigioco!",npc.getName(), JOptionPane.INFORMATION_MESSAGE);
            initGame(); // Avvia il minigioco solo quando necessario
        }

        if (isActive) {
            bootleMovement(keyHandler);

            // Aggiorna palline
            Iterator<BallEntity> it = balls.iterator();
            while (it.hasNext()) {
                BallEntity ball = it.next();
                ball.update();

                if (bottle.catchBall(ball)) {
                    it.remove();
                    score++;
                    if (score >= targetScore) {
                        isSolved = true;
                        isActive = false;
                        gameState.getPlayer().setMovingEnabler(true);

                        // Risolvi la porta associata
                        if (!gameState.getCurrentRoom().getDoors().isEmpty()) {
                            Door firstDoor = gameState.getCurrentRoom().getDoors().get(0);
                            firstDoor.setSolved(true);
                            gameState.updateDoorState(gameState.getCurrentRoom().getRoomID().id(), true);
                        }
                    }
                } else if (ball.isOffScreen(GamePanel.ORIGINAL_HEIGHT)) {
                    loseball++;
                    it.remove();
                    if (loseball >= 3) { // Perdi il gioco dopo 3 palline perse
                        isActive = false;
                        gameState.getPlayer().setMovingEnabler(true);
                    }
                }
            }

            // Spawna nuove palline ogni 60 frame (~1 secondo)
            ballSpawnTimer++;
            if (ballSpawnTimer > 60) {
                ballSpawnTimer = 0;
                int x = (int) (Math.random() * (GamePanel.ORIGINAL_WIDTH - 200)) + 100; // Limita la posizione di spawn
                balls.add(new BallEntity(x, 30));
            }
        }
    }

    private void bootleMovement(KeyHandler keyHandler) {
        if (keyHandler.leftPressed) {
            bottle.moveLeft();
        }
        if (keyHandler.rightPressed) {
            bottle.moveRight(GamePanel.ORIGINAL_WIDTH);
        }
    }

    public BottleEntity bottle() {
        return bottle;
    }
    
    public List<BallEntity> balls() {
        return balls;
    }

    public int score() {
        return score;
    }

    @Override
    public void drawGame(Graphics2D g2) {
        if(isActive) {
            gui.draw(g2);
        }
    }
    


}