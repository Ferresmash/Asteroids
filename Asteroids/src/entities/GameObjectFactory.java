package entities;

import asteroidState.AsteroidState;
import pos.Force;
import pos.Position;

public class GameObjectFactory {
	
	public GameObject createAsteroid(AsteroidState asteroidState) {
		return new Asteroid(asteroidState);
	}
	
	public GameObject createAsteroid(Position startPos, Force startForce) {
		return new Asteroid(startPos, startForce);
	}
	
	public GameObject createAsteroid(Position startPos, Force startForce, double size) {
		return new Asteroid(startPos, startForce, size);
	}
	
	public GameObject createUfo(Position startPos, Force startForce) {
		return new UFO(startPos, startForce);
	}
	
	public GameObject createBullet(Position startPos, Force startForce) {
		return new Bullet(startPos, startForce);
	}

}
