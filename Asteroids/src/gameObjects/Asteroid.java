package gameObjects;

import java.awt.Polygon;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import asteroidState.AsteroidState;
import asteroidState.LargeState;
import pos.Force;
import pos.Position;
import singleton.GameManager;
import view.RenderVisitor;

public class Asteroid extends GameObject implements Drawable {

	private AsteroidState asteroidState;
	private int nbrOfCorners;
	private int[] xPoints;
	private int[] yPoints;
	private final int leastAmountOfCorners = 6;
	

	public Asteroid(Position startPos, Force startForce) {
		this(new LargeState(), startPos, startForce);
	}

	public Asteroid(AsteroidState asteroidState, Position startPos, Force startForce) {
		this.asteroidState = asteroidState;
		setPosition(startPos);
		setForce(startForce);
		setSize(asteroidState.getSize());
		createRandomizedShape();
	}

	public void createRandomizedShape() {
		Random rand = new Random();
		this.nbrOfCorners = leastAmountOfCorners + (rand.nextInt(4));
		xPoints = new int[nbrOfCorners];
		yPoints = new int[nbrOfCorners];

		for (int i = 0; i < nbrOfCorners; i++) {
			xPoints[i] = (int) ((Math.cos((2 * Math.PI) / nbrOfCorners * i) * size) + rand.nextDouble(size)
					- (size / 2));
			yPoints[i] = (int) ((Math.sin((2 * Math.PI) / nbrOfCorners * i) * size) + rand.nextDouble(size)
					- (size / 2));
		}

	}

	@Override
	public void accept(RenderVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public Shape getHitbox() {
		int[] newXPoints = new int[nbrOfCorners];
		int[] newYPoints = new int[nbrOfCorners];
		for (int i = 0; i < nbrOfCorners; i++) {
			newXPoints[i] = xPoints[i] + (int) getPosition().getX();
			newYPoints[i] = yPoints[i] + (int) getPosition().getY();
		}
		return new Polygon(newXPoints, newYPoints, nbrOfCorners);
	}

	public int getNbrOfCorners() {
		return nbrOfCorners;

	}

	public void setNbrOfCorners(int nbrOfCorners) {
		this.nbrOfCorners = nbrOfCorners;
	}

	public int[] getxPoints() {
		return xPoints;
	}

	public void setxPoints(int[] xPoints) {
		this.xPoints = xPoints;
	}

	public int[] getyPoints() {
		return yPoints;
	}

	public void setyPoints(int[] yPoints) {
		this.yPoints = yPoints;
	}

	@Override
	public void getHit(List<GameObject> allAsteroids) {
		GameManager.getInstance().increaseScore(100);
		allAsteroids.addAll(asteroidState.splitAsteroid(this));
		allAsteroids.remove(this);
	}

	public List<GameObject> createSmallerAsteroids(AsteroidState state) {
		List<GameObject> newAsteroids = new ArrayList<GameObject>();
		Force force = getForce();
		force.rotate(0.5);
		newAsteroids.add(new Asteroid(state, getPosition(), force));
		force = getForce();
		force.rotate(-0.5);
		newAsteroids.add(new Asteroid(state, getPosition(), force));
		return newAsteroids;
	}

	public void setState(AsteroidState asteroidState) {
		this.asteroidState = asteroidState;
	}

}
