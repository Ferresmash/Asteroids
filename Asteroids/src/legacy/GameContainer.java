package legacy;

import java.util.List;
import entities.GameObject;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import entities.Drawable;

public class GameContainer extends JPanel {

	private static final long serialVersionUID = 1L;

	private EntityHandler entityHandler = new EntityHandler();

	private int screenWidth = 1000;
	private int screenHeight = 700;
	private Timer UfoTimer;
	private Timer UfoShootingTimer;
	boolean readyToShoot = false;
	boolean readyToSpawn = false;


	public GameContainer(int width, int height) {
		super();
		this.screenWidth = width;
		this.screenHeight = height;
		entityHandler.getPlayer().setPosition(width/2, height/2);

		spawnAsteroid();
		spawnAsteroid();
//		UfoTimer = new Timer(10000, e -> spawnUfo());
//		UfoTimer.start();
//		UfoShootingTimer = new Timer(10, e -> shootFromUfos());
//		UfoShootingTimer.start();

	}

	public void updateContainer(boolean WKeyPressed, boolean AKeyPressed, boolean DKeyPressed,
			boolean SpaceKeyPressed) {

		if (entityHandler.getEnemyHandler().getAsteroids().size() == 0) {
			GameManager.getInstance().increaseLevel();
			for(int i = 0; i < GameManager.getInstance().getLevel(); i++) {
				spawnAsteroid();
			}		

		}
		
		for (GameObject gameObject : entityHandler.getGameObjects()) {
			if (gameObject != null)
				gameObject.move();
		}
		
		entityHandler.getPlayer().move();
		if (WKeyPressed) {
			entityHandler.getPlayer().accelerate();
		} else {
			entityHandler.getPlayer().setAccelerating(false);
		}
		

		if (AKeyPressed) {
			entityHandler.getPlayer().rotate(0.1);
		}
		
		if (DKeyPressed) {
			entityHandler.getPlayer().rotate(-0.1);
		}
		
		if (SpaceKeyPressed) {
			spawnBullet();
		}
		for(GameObject gameObject : entityHandler.getGameObjects()) {
			keepOnScreen(gameObject);
		}
		removeIfOffScreen(entityHandler.getBullets());
		removeIfOffScreen(entityHandler.getEnemyBullets());
		checkCollision();
		if(readyToShoot) {
			shootFromUfos();
			readyToShoot = false;
		}
		if(readyToSpawn) {
			spawnUfo();
			readyToSpawn = false;
		}
	}

	private void spawnAsteroid() {
		// System.out.println("Spawned Asteroid");
		entityHandler.getEnemyHandler().spawnAsteroid(screenWidth, screenHeight);
	}

	public void spawnUfo() {
		// System.out.println("Spawned UFO");
		SwingUtilities.invokeLater(() -> {
			entityHandler.getEnemyHandler().spawnUFO(screenWidth, screenHeight);
		});
	}

	public void spawnBullet() {
		SwingUtilities.invokeLater(() -> {
			entityHandler.addBullet();
		});
		
	}
	
	public void spawnEnemyBullet(GameObject ufo) {
		SwingUtilities.invokeLater(() -> {
			entityHandler.addEnemyBullet(ufo);
		});
		
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

	public void reset() {
		entityHandler = new EntityHandler();
	}
	
}
