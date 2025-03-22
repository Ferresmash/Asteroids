package asteroidState;

import java.util.List;

import entities.Asteroid;
import entities.GameObject;

public interface AsteroidState {

	public void getAsteroid(List<GameObject> gameObjects, Asteroid parent);
	
}
