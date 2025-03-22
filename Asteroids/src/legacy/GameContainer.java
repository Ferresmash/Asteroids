package legacy;

import java.util.List;
import entities.Bullet;
import entities.Enemy;
import entities.GameObject;
import entities.UFO;

import javax.swing.JPanel;
import javax.swing.Timer;
import entities.Drawable;

public class GameContainer extends JPanel {

	private static final long serialVersionUID = 1L;

	private EntityHandler entityHandler = new EntityHandler();

	private int screenWidth = 1000;
	private int screenHeight = 700;
	private Timer UfoTimer;
	private Timer UfoShootingTimer;

	public GameContainer(int width, int height) {
		super();
		this.screenWidth = width;
		this.screenHeight = height;
		entityHandler.getPlayer().setPosition(width/2, height/2);
		UfoTimer = new Timer(10000, e -> spawnUfo());
		UfoTimer.start();
		UfoShootingTimer = new Timer(10, e -> shootFromUfos());
		UfoShootingTimer.start();
	}

	public void updateContainer(boolean WKeyPressed, boolean AKeyPressed, boolean DKeyPressed,
			boolean SpaceKeyPressed) {
		
		if (entityHandler.getEnemyHandler().getAsteroids().size() < 5) {
			spawnAsteroid();
		}

		for (GameObject enemy : entityHandler.getEnemyHandler().getEnemies()) {
			if (enemy != null)
				enemy.move();
		}
		
		for (GameObject bullet : entityHandler.getBullets()) {
			bullet.move();
		}
		for (GameObject bullet : entityHandler.getEnemyBullets()) {
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
		for(GameObject gameObject : entityHandler.getGameObjects()) {
			keepOnScreen(gameObject);
		}
		removeIfOffScreen(entityHandler.getBullets());
		removeIfOffScreen(entityHandler.getEnemyBullets());
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
	
	public void spawnEnemyBullet(GameObject ufo) {

		entityHandler.addEnemyBullet(ufo);
	}
	
	public void shootFromUfos() {
		for(GameObject ufo : entityHandler.getEnemyHandler().getUfos()) {
			spawnEnemyBullet(ufo);
		}
	}
	

	public void checkCollision() {
		entityHandler.checkAllCollisions();
	}
	
	public void removeIfOffScreen(List<GameObject> gameObjects) {
		for(int i = gameObjects.size()-1; i >= 0; i--) {
			//GameObject gameObject = gameObjects.get(i);
			double x = gameObjects.get(i).getPosition().getX();
			double y = gameObjects.get(i).getPosition().getY();
			int margin = 50;
			if(x < 0 || x > screenWidth || y < 0 || y > screenHeight) {
				gameObjects.remove(i);
				System.out.println("remove bullet");
			}
		}
	}
	
	public void keepOnScreen(GameObject gameObject) {
		double x = gameObject.getPosition().getX();
		double y = gameObject.getPosition().getY();
		int margin = 50;
		if(x < -margin) 
			gameObject.getPosition().setX(screenWidth+margin);
		else if(x > screenWidth+margin) 
			gameObject.getPosition().setX(-margin);
		if(y < -margin) 
			gameObject.getPosition().setY(screenHeight+margin);
		else if(y > screenHeight+margin) 
			gameObject.getPosition().setY(-margin);
	}

	public List<Drawable> getEntities() {
		return entityHandler.getEntities();
	}
}
