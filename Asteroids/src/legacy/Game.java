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
	
	Asteroid a = new Asteroid();
	Player player = new Player();
	UFO ufo = new UFO();
	List<Bullet> bullets = new ArrayList<Bullet>();
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
        
        for(Bullet b : bullets) {
        	b.draw(g);
        }
        player.draw(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Example animation logic
    	
    	if(WKeyPressed) {
    		player.accelerate();
    	}
    	player.move();
    	for(Bullet b : bullets) {
        	b.move();
        }
    	
    	if(AKeyPressed) {
    		
    		player.angle+=0.1;
    	}
    	if(DKeyPressed) {
    		player.angle-=0.1;
    	}
    	if(SpaceKeyPressed) {
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
		int keyCode = e.getKeyCode();
		
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
			System.out.println("pressed");
			Position pos = new Position(player.getX(), player.getY());
			
	        bullets.add(new Bullet(pos, -player.angle));
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
