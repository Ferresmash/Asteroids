package legacy;

import java.awt.Graphics;

import entities.Enemy;
import entities.EnemyHandler;
import entities.Entity;
import entities.Player;

import javax.swing.JPanel;


public class GameContainer extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private EnemyHandler enemyHandler = new EnemyHandler();
	private Player player = new Player();


	public GameContainer() {
		super();
	}
	
	public void updateContainer() {
		if(enemyHandler.getEnemies().size() < 5) {
			spawnAsteroid();
		}
		
		for (Enemy enemy : enemyHandler.getEnemies()) {
			enemy.move();
		}
	}
	
	private void spawnAsteroid() {
		System.out.println("Spawned Asteroid");
		enemyHandler.spawnAsteroid(1920, 1080);
	}
	
	public void spawnUfo() {
		System.out.println("Spawned UFO");
		enemyHandler.spawnUFO(1920, 1080);
	}
	
	public void setScreenSize(int width, int heigth) {
		for (Enemy enemy : enemyHandler.getEnemies()) {
			enemy.setScreenSize(width,heigth);
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Entity enemy : enemyHandler.getEnemies()) {
			enemy.draw(g);
		}
		player.draw(g);
			
	}
	
	

  

}
