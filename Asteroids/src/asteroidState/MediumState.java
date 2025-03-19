package asteroidState;

import java.util.Random;

import entities.Asteroid;

public class MediumState implements AsteroidState {

	@Override
	public Asteroid getAsteroid() {
		Asteroid a = new Asteroid(new SmallState());	
		a.setSize(Math.random() * 50);
		return a;
	}

}
