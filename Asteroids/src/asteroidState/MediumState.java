package asteroidState;

import java.util.List;

import entities.Asteroid;
import entities.GameObject;
import pos.Force;
import pos.Position;

public class MediumState implements AsteroidState {

	private final int SIZE = 33;
	
	@Override
	public void splitAsteroid(List<GameObject> gameObjects, Asteroid parent) {
		Force force = new Force(parent.getForce().getX(),parent.getForce().getY());
		force.rotate(0.5);
		Asteroid a = new Asteroid(new SmallState(),parent.getPosition(),force);
		gameObjects.add(a);
		
		Force secondforce = new Force(parent.getForce().getX(),parent.getForce().getY());
		secondforce.rotate(-0.5);
		a = new Asteroid(new SmallState(),parent.getPosition(),secondforce);
		gameObjects.add(a);
	}

	@Override
	public double getSize() {
		// TODO Auto-generated method stub
		return SIZE;
	}
}
