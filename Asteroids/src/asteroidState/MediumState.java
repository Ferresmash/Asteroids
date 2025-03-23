package asteroidState;

import java.util.List;

import entities.Asteroid;
import entities.GameObject;

public class MediumState implements AsteroidState {

	private final int SIZE = 33;
	
	public List<GameObject> splitAsteroid(Asteroid parent) {
		return parent.splitAsteroid(getNextState());
	}
	
	public AsteroidState getNextState() {
		return new SmallState();
	}

	@Override
	public double getSize() {
		return SIZE;
	}
}
