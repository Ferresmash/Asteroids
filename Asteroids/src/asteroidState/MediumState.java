package asteroidState;

import entities.Asteroid;
import legacy.EntityHandler;

public class MediumState implements AsteroidState {

	@Override
	public Asteroid getAsteroid(EntityHandler entityHandler, Asteroid parent) {
		Asteroid a = new Asteroid(new MediumState());
		entityHandler.getEnemyHandler().addEnemy(a);
		entityHandler.getEnemyHandler().addEnemy(a);
		System.out.println("create new smaller asteroid");
		return a;
	}
}
