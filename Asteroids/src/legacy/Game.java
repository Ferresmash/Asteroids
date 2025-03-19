package legacy;

import javax.swing.*;

import entities.Asteroid;
import entities.Player;
import pos.Position;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JPanel implements ActionListener, KeyListener {
	Asteroid a = new Asteroid();
	Player player = new Player();
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
        player.draw(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Example animation logic
    	
    	if(WKeyPressed) {
    		player.accelerate();
    	}
    	if(AKeyPressed) {
    		System.out.println(AKeyPressed);
    		player.angle-=0.01;
    	}
    	if(DKeyPressed) {
    		player.angle+=0.01;
    	}
    	if(SpaceKeyPressed) {
    		System.out.println(SpaceKeyPressed);
    	}
    	
    	Position pos = a.getPosition();
        pos.setX(pos.getX()+1);
        a.setPosition(pos);
        

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
    /*
    public void render() {

        Graphics g = getGraphics();
        g.clearRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
        g.setColor(Color.black);
        g.drawRect(50, 50, 50, 50);
        Position pos = a.getPosition();
        pos.setX(pos.getX()+1);
        a.setPosition(pos);
        a.draw(g);
    }*/


