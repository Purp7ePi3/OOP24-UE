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
        setLayout(new GridLayout(3, 1));

        // Creazione dei pulsanti
        JButton playButton = new JButton("Gioca");
        JButton optionsButton = new JButton("Opzioni");
        JButton exitButton = new JButton("Esci");

        // Aggiunta degli ascoltatori di eventi sui pulsanti
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Quando clicchi "Gioca", rimuoviamo il menu e carichiamo il gioco
                window.getContentPane().removeAll();
                window.getContentPane().add(new GamePanel(gameController, puzzleController)); // Carica il GamePanel
                window.setSize(800, 800);
                window.revalidate();
                window.repaint();
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
        add(playButton);
        add(optionsButton);
        add(exitButton);
    }
}
