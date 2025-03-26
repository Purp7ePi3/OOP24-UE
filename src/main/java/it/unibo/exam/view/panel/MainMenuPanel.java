package it.unibo.exam.view.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import it.unibo.exam.controller.game.GameController;
import it.unibo.exam.controller.puzzle.PuzzleController;

public class MainMenuPanel extends JPanel {
    
    public MainMenuPanel(JFrame window, GameController gameController, PuzzleController puzzleController) {
        // Layout del pannello per allineare i pulsanti
        setLayout(new BorderLayout());

        // Crea il pannello centrale che conterrà i pulsanti
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));  // Colonna verticale per i pulsanti
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Creazione dei pulsanti
        JButton playButton = new JButton("Gioca");
        JButton optionsButton = new JButton("Opzioni");
        JButton exitButton = new JButton("Esci");

        playButton.setPreferredSize(new Dimension(1920, 1080));  // Impostare la larghezza per tutti i pulsanti
        optionsButton.setPreferredSize(new Dimension(1920, 1080));
        exitButton.setPreferredSize(new Dimension(1920, 1080));

        // Aggiunta degli ascoltatori di eventi sui pulsanti
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Quando clicchi "Gioca", rimuoviamo il menu e carichiamo il gioco
                window.getContentPane().removeAll();
                final GamePanel gamePanel = new GamePanel(gameController, puzzleController); // Update to pass PuzzleController
                window.add(gamePanel);
                gamePanel.requestFocusInWindow();//Aggiungo il focus per ricevere gli input da tastiera
                window.pack();
                window.setLocationRelativeTo(null);
                window.setVisible(true);
                gamePanel.startGameThread();
            }
        });

        optionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Qui puoi aggiungere la logica per il pannello delle opzioni
                JOptionPane.showMessageDialog(window, "Opzioni non implementate ancora.");
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);  // Esce dal programma
            }
        });

        // Aggiungiamo i pulsanti al pannello
        buttonPanel.add(Box.createVerticalStrut(100));// Spazio vuoto sopra i pulsanti
        buttonPanel.add(Box.createHorizontalStrut(100));  
        buttonPanel.add(playButton);
        buttonPanel.add(Box.createVerticalStrut(20));   // Spazio tra i pulsanti
        buttonPanel.add(optionsButton);
        buttonPanel.add(Box.createVerticalStrut(20));   // Spazio tra i pulsanti
        buttonPanel.add(exitButton);
        buttonPanel.add(Box.createVerticalStrut(100));  // Spazio vuoto sotto i pulsanti

        // Aggiungi il pannello dei pulsanti al pannello principale
        add(buttonPanel, BorderLayout.CENTER);
    }
}
