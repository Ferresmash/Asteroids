package model;

import java.util.List;
import gameObjects.GameObject;
import pos.Force;
import singleton.GameManager;
import gameObjects.Drawable;

public class Model {

	private EntityHandler entityHandler;

	private int screenWidth;
	private int screenHeight;


	public Model(int width, int height) {
		super();
		this.screenWidth = width;
		this.screenHeight = height;
		entityHandler = new EntityHandler(width, height);

		spawnAsteroid();
		spawnAsteroid();

	}
	
	public void accelerate() {
		entityHandler.getPlayer().accelerate();
	}
	
	public void stopAcceleration() {
		entityHandler.getPlayer().setAccelerating(false);
	}
	
	public void turnRight() {
		entityHandler.getPlayer().rotateRight();
	}
	
	public void turnLeft() {
		entityHandler.getPlayer().rotateLeft();
	}
	
	public void checkForLevelUp() {
		if (entityHandler.getEnemyHandler().getAsteroids().size() == 0) {
			GameManager.getInstance().increaseLevel();
			for (int i = 0; i < GameManager.getInstance().getLevel(); i++) {
				spawnAsteroid();
			}

		}
	}
	
	public void moveObjects() {
		for (GameObject gameObject : entityHandler.getGameObjects()) {
			if (gameObject != null)
				gameObject.move();
		}
		entityHandler.getPlayer().move();
	}
	
	public void removeObjectsOffScreen() {
		removeIfOffScreen(entityHandler.getBullets());
		removeIfOffScreen(entityHandler.getEnemyBullets());
	}
	
	public void keepObjectsOnScreen() {
		for (GameObject gameObject : entityHandler.getEnemyHandler().getAsteroids()) {
			keepOnScreen(gameObject, 50);
			gameObject.rotate(0.01);
		}
		for (GameObject gameObject : entityHandler.getEnemyHandler().getUfos()) {
			keepOnScreen(gameObject, 20);
		}
		keepOnScreen(entityHandler.getPlayer(), 20);
	}

	private void spawnAsteroid() {
		// System.out.println("Spawned Asteroid");
		entityHandler.getEnemyHandler().spawnAsteroid(screenWidth, screenHeight);
	}

	public void spawnUfo() {
			entityHandler.getEnemyHandler().spawnUFO(screenWidth, screenHeight);
	}

	public void spawnBullet() {
			entityHandler.addBullet();
	}

	public void spawnEnemyBullet(GameObject ufo) {
			entityHandler.addEnemyBullet(ufo);
	}

	public void shootFromUfos() {
		for (GameObject ufo : entityHandler.getEnemyHandler().getUfos()) {
			spawnEnemyBullet(ufo);
			Force force = ufo.getForce();
			force.setAngle(Math.random() * Math.PI * 2);
			ufo.setForce(force);
		}
	}

	public void checkCollision() {
		entityHandler.checkAllCollisions();
	}

	public void removeIfOffScreen(List<GameObject> gameObjects) {
		int margin = 200;
		for (int i = gameObjects.size() - 1; i >= 0; i--) {
			double x = gameObjects.get(i).getX();
			double y = gameObjects.get(i).getY();
			if (x < -margin || x > screenWidth + margin || y < -margin || y > screenHeight + margin) {
				gameObjects.remove(i);
			}
		}
	}

	public void keepOnScreen(GameObject gameObject, int margin) {
		double x = gameObject.getX();
		double y = gameObject.getY();
		if (x < -margin)
			gameObject.setPosition(screenWidth + margin, y);
		else if (x > screenWidth + margin)
			gameObject.setPosition(-margin, y);
		else if (y < -margin)
			gameObject.setPosition(x, screenHeight + margin);
		else if (y > screenHeight + margin)
			gameObject.setPosition(x, -margin);
	}

	public List<Drawable> getDrawables() {
		return entityHandler.getDrawables();
	}

	public void reset() {
		entityHandler = new EntityHandler(screenWidth, screenHeight);
	}

}
