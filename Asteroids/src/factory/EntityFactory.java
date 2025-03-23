package factory;

import asteroidState.AsteroidState;
import gameObjects.GameObject;
import pos.Force;
import pos.Position;

public interface EntityFactory {

	public GameObject createAsteroid(AsteroidState asteroidState, Position startPos, Force startForce);

	public GameObject createAsteroid(Position startPos, Force startForce);

	public GameObject createUfo(Position startPos, Force startForce);

	public GameObject createBullet(Position startPos, Force startForce);
	
}
