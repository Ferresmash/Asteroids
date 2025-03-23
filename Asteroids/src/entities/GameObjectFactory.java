package entities;

import asteroidState.AsteroidState;
import pos.Force;
import pos.Position;

public class GameObjectFactory {
	
	public GameObject createAsteroid(AsteroidState asteroidState, Position startPos, Force startForce) {
		return new Asteroid(asteroidState, startPos, startForce);
	}
	
	public GameObject createAsteroid(Position startPos, Force startForce) {
		return new Asteroid(startPos, startForce);
	}
	
	public GameObject createUfo(Position startPos, Force startForce) {
		return new UFO(startPos, startForce);
	}
	
	public GameObject createBullet(Position startPos, Force startForce) {
		return new Bullet(startPos, startForce);
	}

}
