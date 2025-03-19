package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pos.Force;
import pos.Position;

public class EnemyHandler {

	private List<Enemy> enemies = new ArrayList<Enemy>();

	public List<Enemy> getEnemies() {
		return enemies;
	}


	public void setEnemies(List<Enemy> enemies) {
		this.enemies = enemies;
	}

	public void spawnAsteroid(int screenWidth, int screenHeight) {	
		System.out.println("width: " +screenWidth );
		System.out.println("ehgith: " +screenHeight);
		enemies.add(new Asteroid(getRandomSpawnPosition(screenWidth, screenHeight),getRandomSpawnForce()));
	}

	public void spawnUFO(int screenWidth, int screenHeight) {
		enemies.add(new UFO(getRandomSpawnPosition(screenWidth, screenHeight),getRandomSpawnForce()));
	}
	
	private Position getRandomSpawnPosition(int screenWidth, int screenHeight) {
		Random rand = new Random();

		int spawnSide = (int) (rand.nextInt(4));
		Position spawnPos = new Position((int)rand.nextInt(screenWidth), (int)rand.nextInt(screenHeight));
		if(spawnSide == 0) {
			spawnPos.setX(0);
		}else if(spawnSide == 1) {
			spawnPos.setY(0);
		}if(spawnSide == 2) {
			spawnPos.setX(screenWidth);
		}if(spawnSide == 3) {
			spawnPos.setY(screenHeight);
		}
		return spawnPos;
	}
	
	private Force getRandomSpawnForce() {
		Random rand = new Random();
		Force force = new Force();
		double speed = 5;
		double angle = rand.nextDouble(2*Math.PI);
		double[] vector = {Math.cos(angle)*speed, Math.sin(angle)*speed};
		force.setDirection(vector);
		return force;
	}

}
