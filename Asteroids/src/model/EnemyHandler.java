package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import factory.GameObjectFactory;
import gameObjects.GameObject;
import pos.Force;
import pos.Position;
import singleton.GameManager;

public class EnemyHandler {

	private GameObjectFactory gameObjectFactory;
	private List<GameObject> asteroids = new ArrayList<GameObject>();
	private List<GameObject> ufos = new ArrayList<GameObject>();
	
	public EnemyHandler() {
		this.gameObjectFactory = new GameObjectFactory();
	}
	
	public List<GameObject> getAsteroids() {
		return asteroids;
	}
	

	public void setAsteroids(List<GameObject> asteroids) {
		this.asteroids = asteroids;
	}

	public List<GameObject> getUfos() {
		return ufos;
	}

	public void setUfos(List<GameObject> ufos) {
		this.ufos = ufos;
	}

	public List<GameObject> getEnemies() {
		List<GameObject> enemies = new ArrayList<GameObject>();
		enemies.addAll(ufos);
		enemies.addAll(asteroids);
		return enemies;
	}


	private static final Random rand = new Random();

	public void spawnAsteroid(int screenWidth, int screenHeight) {
		asteroids.add(gameObjectFactory.createAsteroid(getRandomSpawnPosition(screenWidth, screenHeight), getRandomSpawnForce()));
	}

	public void spawnUFO(int screenWidth, int screenHeight) {
		ufos.add(gameObjectFactory.createUfo(getRandomSpawnPosition(screenWidth, screenHeight), getRandomSpawnForce()));
	}

	private Position getRandomSpawnPosition(int screenWidth, int screenHeight) {
		int margin = 100;
		int spawnSide = (int) (rand.nextInt(4));
		Position spawnPos = new Position(rand.nextInt(screenWidth+margin*2)-margin, rand.nextInt(screenHeight+margin*2)-margin);
		if (spawnSide == 0) {
			spawnPos.setX(-margin);
		} else if (spawnSide == 1) {
			spawnPos.setY(-margin);
		} else if (spawnSide == 2) {
			spawnPos.setX(screenWidth+margin);
		} else if (spawnSide == 3) {
			spawnPos.setY(screenHeight+margin);
		}
		return spawnPos;
	}

	private Force getRandomSpawnForce() {
		double speed = GameManager.getInstance().getLevel();
		double angle = rand.nextDouble(2) * Math.PI;
		Force force = new Force(Math.cos(angle) * speed, Math.sin(angle) * speed);
		return force;
	}

}
