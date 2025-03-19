package entities;

import java.awt.Graphics;
import java.awt.Polygon;
import java.util.Random;

import asteroidState.AsteroidState;

public class Asteroid extends Enemy {

	private AsteroidState asteroidState;
	private Polygon shape;

	public Asteroid(AsteroidState asteroidState) {
		this.asteroidState = asteroidState;
		createShape();
	}
	
	public void createShape() {
		int x = getPosition().getX();
		int y = getPosition().getY();
		Random rand = new Random();
		int[] xPoints = {x + (50 + rand.nextInt()*20), 70, 90, 85, 60, 40, 30}; 
		int[] yPoints = {30, 20, 40, 70, 80, 60, 50};
        int numPoints = xPoints.length;
		shape = new Polygon(xPoints,yPoints, numPoints);
	}

	@Override
	public void draw(Graphics g) {
		g.drawPolygon(shape);		
	}



}
