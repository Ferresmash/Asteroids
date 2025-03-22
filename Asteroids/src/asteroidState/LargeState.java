package asteroidState;

import java.util.List;

import entities.Asteroid;
import entities.GameObject;
import pos.Force;
import pos.Position;

public class LargeState implements AsteroidState {

	@Override
	public void getAsteroid(List<GameObject> gameObjects, Asteroid parent) {
		Position pos = new Position(parent.getPosition().getX(), parent.getPosition().getY());
		Force force = new Force(parent.getForce().getX(), parent.getForce().getY());
		Asteroid a = new Asteroid(new Position(parent.getPosition().getX(), parent.getPosition().getY()), new Force(parent.getForce().getX(), parent.getForce().getY()), parent.getSize()/1.5);
		a.getForce().setAngle(parent.getForce().getAngle()+0.5);
		a.setState(new MediumState());
		gameObjects.add(a);
		a = new Asteroid(new Position(parent.getPosition().getX(), parent.getPosition().getY()), new Force(parent.getForce().getX(), parent.getForce().getY()), parent.getSize()/1.5);
		a.getForce().setAngle(parent.getForce().getAngle()-0.5);
		a.setState(new MediumState());
		gameObjects.add(a);
		System.out.println("create new smaller asteroid");
	}

}
