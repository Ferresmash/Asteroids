package asteroidState;

import entities.Asteroid;
import legacy.EntityHandler;

public class SmallState implements AsteroidState {
	
	public SmallState() {
		
	}

	@Override
	public Asteroid getAsteroid(EntityHandler entityHandler, Asteroid parent) {
		return null;
	}

}
