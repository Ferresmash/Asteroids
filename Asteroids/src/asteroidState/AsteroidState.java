package asteroidState;

import entities.Asteroid;
import legacy.EntityHandler;

public interface AsteroidState {

	public Asteroid getAsteroid(EntityHandler entityHandler, Asteroid parent);
	
}
