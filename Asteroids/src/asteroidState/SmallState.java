package asteroidState;

import java.util.ArrayList;
import java.util.List;

import gameObjects.Asteroid;
import gameObjects.GameObject;

public class SmallState implements AsteroidState {
	
	private final int SIZE = 20;
	
	public List<GameObject> splitAsteroid(Asteroid parent) {
		return new ArrayList<>();
	}
	
	public AsteroidState getNextState() {
		return null;
	}	

	@Override
	public double getSize() {
		return SIZE;
	}

}
