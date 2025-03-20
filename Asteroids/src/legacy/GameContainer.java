package legacy;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import entities.Bullet;
import entities.Enemy;
import entities.EnemyHandler;
import entities.Entity;
import entities.Player;
import pos.Position;

import javax.swing.JPanel;


public class GameContainer extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private EnemyHandler enemyHandler = new EnemyHandler();
	private Player player = new Player();
	private List<Bullet> bullets = new ArrayList<Bullet>();
	private int screenWidth = 1000;
	private int screenHeight = 700;
	
    boolean WKeyPressed = false;
    boolean AKeyPressed = false;
    boolean DKeyPressed = false;
    boolean SpaceKeyPressed = false;


	public GameContainer(int width, int height) {
		super();
		this.screenWidth = width;
		this.screenHeight = height;
	}
	
	public void updateContainer() {
		if(enemyHandler.getEnemies().size() < 5) {
			spawnAsteroid();
		}
		
		for (Enemy enemy : enemyHandler.getEnemies()) {
			enemy.move();
		}
		for (Bullet b : bullets) {
			b.move();
		}
		if(WKeyPressed) {
    		player.accelerate();
    	}
    	player.move();
    	
    	if(AKeyPressed) {
    		//System.out.println(AKeyPressed);
    		player.angle+=0.15;
    	}
    	if(DKeyPressed) {
    		player.angle-=0.15;
    	}
    	if(SpaceKeyPressed) {
    		//System.out.println(SpaceKeyPressed);
    		
    	}
	}
	
	private void spawnAsteroid() {
		//System.out.println("Spawned Asteroid");
		enemyHandler.spawnAsteroid(screenWidth, screenHeight);
	}
	
	public void spawnUfo() {
		//System.out.println("Spawned UFO");
		enemyHandler.spawnUFO(screenWidth, screenHeight);
	}
	
	public void setScreenSize(int width, int heigth) {
		for (Enemy enemy : enemyHandler.getEnemies()) {
			enemy.setScreenSize(width,heigth);
		}
		player.setScreenSize(width,heigth);
		screenHeight = heigth;
		screenWidth = width;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Entity enemy : enemyHandler.getEnemies()) {
			enemy.draw(g);
		}
		for (Bullet b : bullets) {
			b.draw(g);
		}
		player.draw(g);
	}

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
			bullets.add(new Bullet(new Position(player.getX(),player.getY()), -player.angle));
	    }
	}
	

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


	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	

  

}
