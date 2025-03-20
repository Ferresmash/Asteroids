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


public class GamePanel extends JPanel implements ActionListener, KeyListener {
	

	private static final long serialVersionUID = 1L;
	public int screenWidth = getWidth();
    public int screenHeight = getHeight();
    private Timer timer;
    private Controller controller;

    public GamePanel(Controller controller) {
    	this.controller = controller;
        setPreferredSize(new Dimension(1000, 700));
        setBackground(Color.BLACK);
        this.addKeyListener(this);
        this.setFocusable(true);
        timer = new Timer(16, this); // ~60 FPS (1000ms / 60 ≈ 16ms)
        timer.start();
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
        
        // Draw example moving object
        g2d.setColor(Color.WHITE);

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
