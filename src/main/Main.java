package main;

import javax.swing.JFrame;

public class Main extends javax.swing.JFrame {

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Game");

        GamePanel gamePanel = new GamePanel(); // create an instance of GamePanel
        window.add(gamePanel);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread(); // Call startGameThread on the instance of GamePanel
    }
}
