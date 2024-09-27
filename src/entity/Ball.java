package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePanel;

public class Ball extends Entity {
    public char direction = 'R';
    public int ySpeed = 3;  // Speed in the Y direction
    public int player1Score = 0;
    public int player2Score = 0;

    GamePanel gp;

    public Ball(GamePanel gp) {
        this.gp = gp;
        setDefaultValues();
    }

    public void setDefaultValues() {
        x = 50; // Starting X position
        y = 238; // Starting Y position
        speed = 12; // X speed
        ySpeed = 4; // Y speed
    }

    public void update() {
        // Move the ball horizontally
        if (direction == 'R') {
            x += speed;
        } else {
            x -= speed;
        }

        // Move the ball vertically
        y += ySpeed;
        if (y>gp.screenHeight) {
        	y+=100;
        }

        // Bounce off the top or bottom of the screens
        if (y <= 0 || y >= gp.screenHeight - gp.tileSize) {
            ySpeed = -ySpeed;  // Reverse the Y direction
        }
       
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.fillRect(x, y, gp.tileSize / 2, gp.tileSize / 2); // Drawing the ball
    }
}