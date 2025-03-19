package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pos.Force;
import pos.Position;

public class EnemyHandler {

	private List<Enemy> enemies = new ArrayList<Enemy>();
	private static final Random rand = new Random();

	public List<Enemy> getEnemies() {
		return enemies;
	}

	public void setEnemies(List<Enemy> enemies) {
		this.enemies = enemies;
	}

	public void spawnAsteroid(int screenWidth, int screenHeight) {
		System.out.println("width: " + screenWidth);
		System.out.println("heigth: " + screenHeight);
		enemies.add(new Asteroid(getRandomSpawnPosition(screenWidth, screenHeight), getRandomSpawnForce()));
	}

	public void spawnUFO(int screenWidth, int screenHeight) {
		enemies.add(new UFO(getRandomSpawnPosition(screenWidth, screenHeight), getRandomSpawnForce()));
	}

	private Position getRandomSpawnPosition(int screenWidth, int screenHeight) {
		int spawnSide = (int) (rand.nextInt(4));
		Position spawnPos = new Position(rand.nextInt(screenWidth), rand.nextInt(screenHeight));
		if (spawnSide == 0) {
			spawnPos.setX(0);
		} else if (spawnSide == 1) {
			spawnPos.setY(0);
		} else if (spawnSide == 2) {
			spawnPos.setX(screenWidth);
		} else if (spawnSide == 3) {
			spawnPos.setY(screenHeight);
		}
		return spawnPos;
	}

	private Force getRandomSpawnForce() {
		Force force = new Force();
		double speed = 5;
		double angle = rand.nextDouble(2 * Math.PI);
		double[] vector = { Math.cos(angle) * speed, Math.sin(angle) * speed };
		force.setDirection(vector);
		return force;
	}

}
