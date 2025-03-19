package entities;

import java.awt.Graphics;
import java.awt.Polygon;
import java.util.Random;

import asteroidState.AsteroidState;

public class Asteroid extends Enemy {

	private AsteroidState asteroidState;
	int nbrOfCorners;
	int[] xPoints;
	int[] yPoints;
	

	public Asteroid(AsteroidState asteroidState) {
		this.asteroidState = asteroidState;
		this.nbrOfCorners = (int) (6 + (Math.random() * 4));
		xPoints = new int[nbrOfCorners];
		yPoints = new int[nbrOfCorners];
	}
	
	public void setPoints() {
		Random rand = new Random();
		for(int i = 0; i < nbrOfCorners; i++) {
			xPoints[i] = (int) ((Math.cos((2 * Math.PI) / nbrOfCorners * i) * getSize())+ ((rand.nextInt()*(getSize()/nbrOfCorners/2))-(getSize()/nbrOfCorners)));
			yPoints[i] = (int) ((Math.sin((2 * Math.PI) / nbrOfCorners * i) * getSize())+ ((rand.nextInt()*(getSize()/nbrOfCorners/2))-(getSize()/nbrOfCorners)));
		}
	}

	@Override
	public void draw(Graphics g) {
		Polygon shape = new Polygon();
		for(int i = 0; i < nbrOfCorners; i++) {
			shape.xpoints[i] = xPoints[i] + getPosition().getX();
			shape.ypoints[i] = yPoints[i] + getPosition().getY();
		}
		g.drawPolygon(shape);		
	}



}
