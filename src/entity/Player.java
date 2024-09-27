package entity;

import java.awt.Color;
import entity.Ball;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Graphics;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
	
	GamePanel gp;
	KeyHandler keyH;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
		this.keyH = keyH;
	}
	public void setDefaultValues() {
		
		x=20;
		y=100;
		speed = 8;
	}
	
	public void update() {
		
		if(keyH.upPressed == true) {
    		y -= speed;
    		System.out.println(y);
    	}
    	if (keyH.downPressed == true) {
    		y += speed;
    		System.out.println(y);
    		
    	}
//    	if (keyH.rightPressed == true) {
//    		x += speed;
//    		System.out.println(x);
//    	}
//    	if (keyH.leftPressed == true) {
//    		x -= speed;
//    		System.out.println(x);
//    	}
    	
    	
    	//If Player Exits the Screen, down here
    	if (x > gp.screenWidth) {
    		x = gp.screenWidth -1;
    	}
    	else if (x < 0) {
    		x = 1;
    	}
    	if (y > gp.screenHeight - 120) {
    		y = gp.screenHeight - 120;
    	}
    	if (y < 0) {
    		y = 1;
    	}
	}
	
	
	public void draw(Graphics2D g2) {
	        
	        
	        // Example drawing: a white square at (100, 100)
	        g2.setColor(Color.white);
	        
	        g2.fillRect(x, y, gp.pongLength, gp.pongHeight);
	}
}
