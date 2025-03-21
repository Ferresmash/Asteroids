package view;

import javax.swing.*;

import controller.Controller;
import entities.Drawable;
import legacy.GameContainer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class GamePanel extends JPanel implements ActionListener, KeyListener {
	

	private static final long serialVersionUID = 1L;
	public int screenWidth = getWidth();
    public int screenHeight = getHeight();
    private Timer timer;
    private Controller controller;
    private int score;
    private int lives;
    private Image heartImage;


    public GamePanel(Controller controller) {
    	this.controller = controller;
        setPreferredSize(new Dimension(1000, 700));
        setBackground(Color.BLACK);
        this.addKeyListener(this);
        this.setFocusable(true);
        score = 0;
        lives = 5;
        timer = new Timer(16, this); // ~60 FPS (1000ms / 60 ≈ 16ms)
        timer.start();
        gethearts();
    }

    private void gethearts() {
            heartImage = new ImageIcon(getClass().getResource("/img/heart.png"))
                .getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	}


	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Clears background
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        screenWidth = getWidth();
        screenHeight = getHeight();
        controller.setScreenSize(screenWidth, screenHeight);
        GraphicsRenderVisitor renderer = new GraphicsRenderVisitor(g);

        for (Drawable entity : controller.getEntities()) {
			entity.accept(renderer);
		}
        

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.drawString("Score: " + score, 20, 30);
        
        int heartX = screenWidth - (lives * 35);
        int heartY = 10;
        for (int i = 0; i < lives; i++) {
            g2d.drawImage(heartImage, heartX + (i * 35), heartY, this);
        }
        //gameContainer.paintComponent(g);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	controller.updateContainer();
        repaint();
    }

 

	@Override
	public void keyTyped(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
	}

	@Override
	public void keyPressed(KeyEvent e) {	
		controller.keyPress(e);
	    
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		controller.keyReleased(e);
	}
}
