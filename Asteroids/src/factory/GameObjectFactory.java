package factory;

import asteroidState.AsteroidState;
import gameObjects.Asteroid;
import gameObjects.Bullet;
import gameObjects.GameObject;
import gameObjects.Player;
import gameObjects.UFO;
import pos.Force;
import pos.Position;

public class GameObjectFactory implements EntityFactory {

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

	@Override
	public Player createPlayer(int width, int height) {
		return new Player(new Position(width/2, height/2));
	}

}
