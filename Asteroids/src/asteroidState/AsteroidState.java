package asteroidState;

import java.util.List;

import gameObjects.Asteroid;
import gameObjects.GameObject;

public interface AsteroidState {
	
	public List<GameObject> splitAsteroid(Asteroid parent);
	
	public AsteroidState getNextState();
	
	public double getSize();
}
