package legacy;

import javax.swing.*;

import entities.Asteroid;
import entities.Player;
import entities.UFO;
import pos.Position;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JPanel implements ActionListener, KeyListener {
	
	Asteroid a = new Asteroid();
	Player player = new Player();
	UFO ufo = new UFO();
    private Timer timer;
    boolean WKeyPressed = false;
    boolean AKeyPressed = false;
    boolean DKeyPressed = false;
    boolean SpaceKeyPressed = false;

    public Game() {
        setPreferredSize(new Dimension(800, 600));
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
        
        // Draw example moving object
        g2d.setColor(Color.WHITE);
        a.draw(g);
        ufo.draw(g);
        player.draw(g);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Example animation logic
    	
    	if(WKeyPressed) {
    		player.accelerate();
    	}
    	player.move();
    	
    	if(AKeyPressed) {
    		System.out.println(AKeyPressed);
    		player.angle+=0.1;
    	}
    	if(DKeyPressed) {
    		player.angle-=0.1;
    	}
    	if(SpaceKeyPressed) {
    		System.out.println(SpaceKeyPressed);
    	}
    	
    	Position pos = a.getPosition();
        pos.setX(pos.getX()+1);
        a.setPosition(pos);
        
        if(player.getX() < -20) {
        	player.setX(820);
        }
        if(player.getX() > 820) {
        	player.setX(-20);
        }
        if(player.getY() < -20) {
        	player.setY(620);
        }
        if(player.getY() > 620) {
        	player.setY(-20);
        }
        

        repaint(); // Triggers paintComponent
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Asteroids");
        Game gamePanel = new Game();
        frame.add(gamePanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
	    int keyCode = e.getKeyCode();
	    if (keyCode == KeyEvent.VK_W) {
	        WKeyPressed = true;
	    }
	    if (keyCode == KeyEvent.VK_A) {
	        AKeyPressed = true;
	    }
	    if (keyCode == KeyEvent.VK_D) {
	        DKeyPressed = true;
	    }
	    if (keyCode == KeyEvent.VK_SPACE) { // Use KeyEvent.VK_SPACE for spacebar
	        SpaceKeyPressed = true;
	    }
	}

	@Override
	public void keyReleased(KeyEvent e) {
	    int keyCode = e.getKeyCode();
	    if (keyCode == KeyEvent.VK_W) {
	        WKeyPressed = false;
	    }
	    if (keyCode == KeyEvent.VK_A) {
	        AKeyPressed = false;
	    }
	    if (keyCode == KeyEvent.VK_D) {
	        DKeyPressed = false;
	    }
	    if (keyCode == KeyEvent.VK_SPACE) { // Use KeyEvent.VK_SPACE for spacebar
	        SpaceKeyPressed = false;
	    }
	}
}
