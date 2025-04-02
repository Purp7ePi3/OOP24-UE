package it.unibo.exam.view.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import it.unibo.exam.controller.game.GameController;
import it.unibo.exam.controller.puzzle.PuzzleController;

public class MainMenuPanel extends JPanel {
    
    public MainMenuPanel(JFrame window, GameController gameController, PuzzleController puzzleController, int maxWidth, int maxHeight) {

        // Use toolKit to get width and height
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();

        int width = (int)(dimension.width * 0.2);
        int height = (int)(dimension.height * 0.2); 
        System.out.println("Larghezza: " + width + "\nAltezza: " + height);
        // Layout del pannello per allineare i pulsanti
        setLayout(new BorderLayout());
        setPreferredSize(window.getSize());

        // Crea il pannello centrale che conterrà i pulsanti
        JPanel buttonPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 0, 20, 0); // Spaziatura tra pulsanti
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER; // Centra i pulsanti
         // Distribuisce lo spazio in altezza

        // Creazione dei pulsanti
        JButton playButton = new JButton("Gioca");
        JButton optionsButton = new JButton("Opzioni");
        JButton exitButton = new JButton("Esci");

        Dimension buttonSize = new Dimension(800, 80);
        playButton.setPreferredSize(buttonSize);
        optionsButton.setPreferredSize(buttonSize);
        exitButton.setPreferredSize(buttonSize);

        // Imposta il font
        Font buttonFont = new Font("Arial", Font.BOLD, 30);
        playButton.setFont(buttonFont);
        optionsButton.setFont(buttonFont);
        exitButton.setFont(buttonFont);

        // Aggiungiamo i pulsanti al pannello
        buttonPanel.add(playButton, gbc);
        gbc.gridy++; // Sposta il prossimo elemento più in basso
        buttonPanel.add(optionsButton, gbc);
        gbc.gridy++; // Sposta il prossimo elemento più in basso
        buttonPanel.add(exitButton, gbc);

        // Aggiunta del pannello alla finestra
        add(buttonPanel, BorderLayout.CENTER);


        // Aggiunta degli ascoltatori di eventi sui pulsanti
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Quando clicchi "Gioca", rimuoviamo il menu e carichiamo il gioco
                window.getContentPane().removeAll();
                final GamePanel gamePanel = new GamePanel(gameController, puzzleController, maxWidth, maxHeight); // Pass maxWidth and maxHeight
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

        
    }
}