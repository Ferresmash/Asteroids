package legacy;

import java.util.List;
import entities.Bullet;
import entities.Enemy;
import entities.GameObject;

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

		for (GameObject enemy : entityHandler.getEnemyHandler().getEnemies()) {
			if (enemy != null)
				enemy.move();
		}
		for (GameObject bullet : entityHandler.getBullets()) {
			bullet.move();
		}

		if (WKeyPressed) {
			entityHandler.getPlayer().accelerate();
		} else {
			entityHandler.getPlayer().setAccelerating(false);
		}
		entityHandler.getPlayer().move();

		if (AKeyPressed) {
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
		// System.out.println("Spawned Asteroid");
		entityHandler.getEnemyHandler().spawnAsteroid(screenWidth, screenHeight);
	}

	public void spawnUfo() {
		// System.out.println("Spawned UFO");
		entityHandler.getEnemyHandler().spawnUFO(screenWidth, screenHeight);
	}

	public void spawnBullet() {

		entityHandler.addBullet();
	}

	public void checkCollision() {
		entityHandler.checkAllCollisions();
	}

	public void setScreenSize(int width, int heigth) {
		for (GameObject enemy : entityHandler.getEnemyHandler().getEnemies()) {
			if (enemy != null)
				enemy.setScreenSize(width, heigth);
		}
		entityHandler.getPlayer().setScreenSize(width, heigth);
		screenHeight = heigth;
		screenWidth = width;
	}

	public List<Drawable> getEntities() {
		return entityHandler.getEntities();
	}

	public void reset() {
		entityHandler = new EntityHandler();
	}
}
