package asteroidState;

import java.util.List;

import entities.Asteroid;
import entities.GameObject;

public class LargeState implements AsteroidState {
	
	private final int SIZE = 50;
	
	public List<GameObject> splitAsteroid(Asteroid parent) {
		return parent.createSmallerAsteroids(getNextState());
	}
	
	public AsteroidState getNextState() {
		return new MediumState();
	}

	@Override
	public double getSize() {
		return SIZE;
	}

}







