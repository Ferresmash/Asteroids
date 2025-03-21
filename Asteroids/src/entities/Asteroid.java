package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Shape;
import java.util.Random;
import asteroidState.AsteroidState;
import asteroidState.LargeState;
import legacy.EntityHandler;
import pos.Force;
import pos.Position;
import view.RenderVisitor;

public class Asteroid extends Enemy implements GameObject, Drawable {

	private AsteroidState asteroidState;
	int nbrOfCorners;


	int[] xPoints;
	int[] yPoints;

	public Asteroid(AsteroidState asteroidState) {
		this.asteroidState = asteroidState;
		this.nbrOfCorners = (int) (6 + (Math.random() * 4));
		xPoints = new int[nbrOfCorners];
		yPoints = new int[nbrOfCorners];
		setSize(100);
		setPosition(new Position(100, 100));
		setForce(new Force());
		setPoints();
	}

	public Asteroid() {
		this(new LargeState());
	}

	public Asteroid(Position startPos, Force startForce) {
		this(new LargeState());
		setSize(50);
		setForce(startForce);
		setPosition(startPos);
		setPoints();
	}

	public void setPoints() {
		Random rand = new Random();
		for (int i = 0; i < nbrOfCorners; i++) {
			xPoints[i] = (int) ((Math.cos((2 * Math.PI) / nbrOfCorners * i) * getSize()) + rand.nextDouble(getSize())
					- (getSize() / 2));
			yPoints[i] = (int) ((Math.sin((2 * Math.PI) / nbrOfCorners * i) * getSize()) + rand.nextDouble(getSize())
					- (getSize() / 2));
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
			newXPoints[i] = xPoints[i]+(int)getPosition().getX();
			newYPoints[i] = yPoints[i]+(int)getPosition().getY();
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
	public void destroy(EntityHandler entityHandler) {
		asteroidState.getAsteroid(entityHandler, this);

	}

}
