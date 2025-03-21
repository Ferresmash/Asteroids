package legacy;

import java.util.List;
import entities.Bullet;
import entities.Enemy;
import javax.swing.JPanel;
import javax.swing.Timer;
import entities.Drawable;

public class GameContainer extends JPanel {

	private static final long serialVersionUID = 1L;

	private EntityHandler entityHandler = new EntityHandler();

	private int screenWidth = 1000;
	private int screenHeight = 700;
	private Timer UfoTimer;

	public GameContainer(int width, int height) {
		super();
		this.screenWidth = width;
		this.screenHeight = height;

		UfoTimer = new Timer(1000, e -> spawnUfo());
		UfoTimer.start();
	}

	public void updateContainer(boolean WKeyPressed, boolean AKeyPressed, boolean DKeyPressed,
			boolean SpaceKeyPressed) {
		
		if (entityHandler.getEnemyHandler().getEnemies().size() < 5) {
			spawnAsteroid();
		}

		for (Enemy enemy : entityHandler.getEnemyHandler().getEnemies()) {
			if(enemy != null)
				enemy.move();
		}
		for (Bullet bullet : entityHandler.getBullets()) {
			bullet.move();
		}

		if (WKeyPressed) {
			System.out.println("accelerating");
			entityHandler.getPlayer().accelerate();
		}else {
			entityHandler.getPlayer().setAccelerating(false);
		}
		entityHandler.getPlayer().move();

		if (AKeyPressed) {
			System.out.println(AKeyPressed);
			entityHandler.getPlayer().rotate(0.1);
		}
		if (DKeyPressed) {
			entityHandler.getPlayer().rotate(-0.1);
		}
		if (SpaceKeyPressed) {
			System.out.print("spawned bullet");
			spawnBullet();
		}
		checkCollision();
	}

	private void spawnAsteroid() {
		//System.out.println("Spawned Asteroid");
		entityHandler.getEnemyHandler().spawnAsteroid(screenWidth, screenHeight);
	}

	public void spawnUfo() {
		//System.out.println("Spawned UFO");
		entityHandler.getEnemyHandler().spawnUFO(screenWidth, screenHeight);
	}
	
	public void spawnBullet() {
		
		entityHandler.addBullet();
	}

	public void checkCollision() {
		entityHandler.checkCollision();
	}

	public void setScreenSize(int width, int heigth) {
		for (Enemy enemy : entityHandler.getEnemyHandler().getEnemies()) {
			if(enemy != null)
				enemy.setScreenSize(width, heigth);
		}
		entityHandler.getPlayer().setScreenSize(width, heigth);
		screenHeight = heigth;
		screenWidth = width;
	}

	public List<Drawable> getEntities() {
		return entityHandler.getEntities();
	}
}
