package legacy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entities.Asteroid;
import entities.Enemy;
import entities.GameObject;
import entities.UFO;
import pos.Force;
import pos.Position;

public class EnemyHandler {

	private List<GameObject> asteroids = new ArrayList<GameObject>();
	private List<GameObject> ufos = new ArrayList<GameObject>();
	
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
		asteroids.add(new Asteroid(getRandomSpawnPosition(screenWidth, screenHeight), getRandomSpawnForce()));
	}

	public void spawnUFO(int screenWidth, int screenHeight) {
		ufos.add(new UFO(getRandomSpawnPosition(screenWidth, screenHeight), getRandomSpawnForce()));
	}

	private Position getRandomSpawnPosition(int screenWidth, int screenHeight) {
		int spawnSide = (int) (rand.nextInt(4));
		Position spawnPos = new Position(rand.nextInt(screenWidth+200)-100, rand.nextInt(screenHeight+200)-100);
		if (spawnSide == 0) {
			spawnPos.setX(-100);
		} else if (spawnSide == 1) {
			spawnPos.setY(-100);
		} else if (spawnSide == 2) {
			spawnPos.setX(screenWidth+100);
		} else if (spawnSide == 3) {
			spawnPos.setY(screenHeight+100);
		}
		System.out.println(spawnPos.getX()+", "+spawnPos.getY());
		return spawnPos;
	}

	private Force getRandomSpawnForce() {
		double speed = 5;
		double angle = rand.nextDouble(2) * Math.PI;
		Force force = new Force(Math.cos(angle) * speed, Math.sin(angle) * speed);
		return force;
	}

}
