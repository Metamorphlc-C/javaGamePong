package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Ball;
import entity.Player;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 3;
    
    public int ySpeed;
    public final int tileSize = originalTileSize * scale;
    public final int pongLength = 24;
    public final int pongHeight = 120;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    final int fps = 60;
    int player1Score = 0;
    int player2Score = 0;
    public boolean isRunning = true;
    
    KeyHandler keyH = new KeyHandler(false, this);  // WASD for Player1
    KeyHandler keyH2 = new KeyHandler(true, this);  // Arrow keys for Player2
    Thread gameThread;
    Player player;
    Player player2;
    Ball ball;
    
    public void isTouching() {
        // Ball touches Player1 paddle
        if (ball.x <= player.x + tileSize && ball.y >= player.y && ball.y <= player.y + pongHeight) {
            ball.direction = 'R'; // Change x direction to the right

            // Adjust ySpeed based on where the ball hits the paddle
            int hitPosition = ball.y - player.y;
            ball.ySpeed = (hitPosition - pongHeight / 2) / 10;  // Change ySpeed dynamically
        }

        // Ball touches Player2 paddle
        if (ball.x >= player2.x - tileSize && ball.y >= player2.y && ball.y <= player2.y + pongHeight) {
            ball.direction = 'L'; // Change x direction to the left

            // Adjust ySpeed based on where the ball hits the paddle
            int hitPosition = ball.y - player2.y;
            ball.ySpeed = (hitPosition - pongHeight / 2) / 10;  // Change ySpeed dynamically
        }
    }
    
    public void togglePause() {
        isRunning = !isRunning; // Toggles the boolean state
        System.out.println(isRunning ? "Game Resumed" : "Game Paused");
    }
    
    
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); 
        this.setBackground(Color.black); 
        this.setDoubleBuffered(true); 
        this.addKeyListener(keyH);    // Key listener for Player1
        this.addKeyListener(keyH2);   // Key listener for Player2
        this.setFocusable(true);
        
        // Initialize players
        player = new Player(this, keyH);
        player2 = new Player(this, keyH2);
        ball = new Ball(this);
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
        player2.setDefaultValues();
        player2.x = 728;
        player2.y = 100;
        player2.speed = 8;
        ball.setDefaultValues();
        while (gameThread != null) {
        	if (isRunning == true) {
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
    }

    public void update() {
        player.update();
        player2.update();  // Ensure Player2 is being updated
        ball.update();
        isTouching();
        if (ball.x > screenWidth) {
        	System.out.println("Player 1 Scored!");
        	player1Score += 1;
        	player.setDefaultValues();
        	player2.x = 728;
        	player2.y = 100;
        	player2.speed = 8;
        	ball.setDefaultValues();
        	
        	
        	
        }
        else if (ball.x < 0) {
        	System.out.println("Player 2 Scored!");
        	player2Score += 1;
        	player.setDefaultValues();
        	player2.x = 728;
        	player2.y = 100;
        	player2.speed = 8;
        	ball.setDefaultValues();
        	ball.direction = 'R';
        	
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;
        
        // Set font and color for the score
        g2.setFont(new Font("Arial", Font.BOLD, 24));
        g2.setColor(Color.WHITE);
        
        // Draw Player1
        player.draw(g2);
        
        // Draw Player2
        player2.draw(g2);  // Make sure Player2 is drawn
        
        // Draw the ball
        ball.draw(g2);
        
        // Draw the scores at the top of the screen
        g2.drawString("Player 1: " + player1Score, 50, 30); // Player 1 score at the top-left
        g2.drawString("Player 2: " + player2Score, screenWidth - 200, 30); // Player 2 score at the top-right
        if (player1Score == 5 || player2Score == 5) {
        	g2.drawString("Game Over!", 300, 300);
        }
        
        g2.dispose();
    }
}