package asteroidState;

import java.util.List;

import entities.Asteroid;
import entities.GameObject;

public class SmallState implements AsteroidState {
	
	private final int SIZE = 15;
	
	public SmallState() {
		
	}

	@Override
	public void splitAsteroid(List<GameObject> gameObjects, Asteroid parent) {

	}

	@Override
	public double getSize() {
		return SIZE;
	}

}
