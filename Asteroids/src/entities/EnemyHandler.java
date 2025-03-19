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
		enemies.add(new Asteroid(getRandomSpawnPosition(screenWidth, screenHeight),getRandomSpawnForce()));
	}

	public void spawnUFO() {
		enemies.add(new UFO(getRandomSpawnPosition(screenWidth, screenHeight),getRandomSpawnForce()));
	}
	
	private Position getRandomSpawnPosition(int screenWidth, int screenHeight) {
		int spawnSide = (int) (Math.random() * 4);
		Position spawnPos = new Position((int)Math.random()*screenWidth, (int)Math.random()*screenHeight);
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
		Force force = new Force();
		double[] direction = {(Math.random()*2),(Math.random()*2)};
		force.setDirection(direction);
		force.setSpeed(Math.random()*5);
		return force;
	}

}
