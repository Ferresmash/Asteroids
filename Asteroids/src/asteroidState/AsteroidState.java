package asteroidState;

import java.util.List;

import entities.Asteroid;
import entities.GameObject;

public interface AsteroidState {

	public void splitAsteroid(List<GameObject> gameObjects, Asteroid parent);
	
	public double getSize();
}
