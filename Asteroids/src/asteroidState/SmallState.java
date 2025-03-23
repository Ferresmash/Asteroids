package asteroidState;

import java.util.ArrayList;
import java.util.List;

import entities.Asteroid;
import entities.GameObject;

public class SmallState implements AsteroidState {
	
	private final int SIZE = 15;
	
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
