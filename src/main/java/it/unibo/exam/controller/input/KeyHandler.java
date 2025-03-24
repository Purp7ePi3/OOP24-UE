package main.java.it.unibo.exam.controller.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed, interactPressed;
    
    @Override
    public void keyTyped(KeyEvent e) {
        // not needed
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W)
            upPressed = true;
            System.out.println("DIOCANE");
        if (code == KeyEvent.VK_S)
            downPressed = true;
        if (code == KeyEvent.VK_A)
            leftPressed = true;
        if (code == KeyEvent.VK_D)
            rightPressed = true;
        if (code == KeyEvent.VK_E)
            interactPressed = true;
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W)
            upPressed = false;
        if (code == KeyEvent.VK_S)
            downPressed = false;
        if (code == KeyEvent.VK_A)
            leftPressed = false;
        if (code == KeyEvent.VK_D)
            rightPressed = false;
        if (code == KeyEvent.VK_E)
            interactPressed = false;
    }
}