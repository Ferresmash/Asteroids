package asteroidState;

import java.util.List;

import entities.Asteroid;
import entities.GameObject;

public interface AsteroidState {
	
	public List<GameObject> splitAsteroid(Asteroid parent);
	
	public AsteroidState getNextState();
	
	public double getSize();
}
