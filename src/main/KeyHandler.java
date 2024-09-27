package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    private boolean useArrowKeys; // Determines which key set to use
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    private GamePanel gamePanel; // Reference to the GamePanel

    // Constructor to decide which key set to use and pass the GamePanel
    public KeyHandler(boolean useArrowKeys, GamePanel gamePanel) {
        this.useArrowKeys = useArrowKeys;
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (useArrowKeys) {
            // Use arrow keys for movement
            if (code == KeyEvent.VK_UP) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_DOWN) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_LEFT) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_RIGHT) {
                rightPressed = true;
            }
        } else {
            // Use WASD keys for movement
            if (code == KeyEvent.VK_W) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_S) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_A) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_D) {
                rightPressed = true;
            }
        }
        if (code == KeyEvent.VK_ESCAPE) {
            // Toggle the game's running state
            gamePanel.togglePause();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (useArrowKeys) {
            if (code == KeyEvent.VK_UP) {
                upPressed = false;
            }
            if (code == KeyEvent.VK_DOWN) {
                downPressed = false;
            }
            if (code == KeyEvent.VK_LEFT) {
                leftPressed = false;
            }
            if (code == KeyEvent.VK_RIGHT) {
                rightPressed = false;
            }
        } else {
            if (code == KeyEvent.VK_W) {
                upPressed = false;
            }
            if (code == KeyEvent.VK_S) {
                downPressed = false;
            }
            if (code == KeyEvent.VK_A) {
                leftPressed = false;
            }
            if (code == KeyEvent.VK_D) {
                rightPressed = false;
            }
        }
    }
}