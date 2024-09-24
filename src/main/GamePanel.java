package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 3;
    
    public final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    final int fps = 60;
    
    KeyHandler keyH = new KeyHandler(false);  // WASD for Player1
    KeyHandler keyH2 = new KeyHandler(true);  // Arrow keys for Player2
    Thread gameThread;
    Player player = new Player(this, keyH);
    Player player2 = new Player(this, keyH2);
    
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); 
        this.setBackground(Color.black); 
        this.setDoubleBuffered(true); 
        this.addKeyListener(keyH);    // Key listener for Player1
        this.addKeyListener(keyH2);   // Key listener for Player2
        this.setFocusable(true);
    }
    
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / fps;
        double nextDrawTime = System.nanoTime() + drawInterval;
        player.setDefaultValues();
        player2.x = 300;
        player2.y = 100;
        player2.speed = 4;
        
        while (gameThread != null) {
            update();
            repaint();
           
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;
                
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                
                Thread.sleep((long) remainingTime);
                
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        player.update();
        player2.update();  // Ensure Player2 is being updated
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;
        
        // Draw Player1
        player.draw(g2);
        
        // Draw Player2
        player2.draw(g2);  // Make sure Player2 is drawn
    }
}