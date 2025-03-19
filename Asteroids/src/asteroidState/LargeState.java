package asteroidState;

import entities.Asteroid;

public class LargeState implements AsteroidState {

	@Override
	public Asteroid getAsteroid() {
		Asteroid a = new Asteroid(new MediumState());
		a.setSize(50 + Math.random() * 50);
		return a;
	}

}
