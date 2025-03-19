package legacy;

import javax.swing.*;


import entities.Asteroid;
import entities.Bullet;
import entities.Player;
import entities.UFO;
import pos.Position;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class Game extends JPanel implements ActionListener, KeyListener {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int screenWidth = getWidth();
    public int screenHeight = getHeight();
    private Timer timer;
    private Timer UfoTimer;
    private GameContainer gameContainer;

    public Game() {
        setPreferredSize(new Dimension(1000, 700));
        setBackground(Color.BLACK);
        this.addKeyListener(this);
        this.setFocusable(true);
        timer = new Timer(16, this); // ~60 FPS (1000ms / 60 ≈ 16ms)
        timer.start();
        gameContainer = new GameContainer(1000,700);
        UfoTimer = new Timer(1000, e -> gameContainer.spawnUfo());
        UfoTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Clears background
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        screenWidth = getWidth();
        screenHeight = getHeight();
        
        gameContainer.setScreenSize(screenWidth, screenHeight);
        
        // Draw example moving object
        g2d.setColor(Color.WHITE);

        gameContainer.paintComponent(g);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Example animation logic
    	

//    	if(WKeyPressed) {
//    		player.accelerate();
//    	}
//    	player.move();
//    	
//    	if(AKeyPressed) {
//    		System.out.println(AKeyPressed);
//    		player.angle-=0.05;
//    	}
//    	if(DKeyPressed) {
//    		player.angle+=0.05;
//    	}
//    	if(SpaceKeyPressed) {
//    		System.out.println(SpaceKeyPressed);
//    	}
//    	
//    	Position pos = a.getPosition();
//        pos.setX(pos.getX()+1);
//        a.setPosition(pos);
        
    	gameContainer.updateContainer();

        repaint(); // Triggers paintComponent
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Asteroids");
        Game gamePanel = new Game();
        frame.add(gamePanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

	@Override
	public void keyTyped(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
	}

	@Override
	public void keyPressed(KeyEvent e) {	
		gameContainer.keyPress(e);
	    
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		gameContainer.keyReleased(e);

	}
}
