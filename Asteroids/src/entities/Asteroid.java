package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.Random;
import asteroidState.AsteroidState;
import asteroidState.LargeState;
import pos.Force;
import pos.Position;

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
	
	public Asteroid() {
		this(new LargeState());
		setSize(100);
		setPosition(new Position(100, 100));
		setForce(new Force());
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
		for(int i = 0; i < nbrOfCorners; i++) {
			xPoints[i] = (int) ((Math.cos((2 * Math.PI) / nbrOfCorners * i) * getSize()) + rand.nextDouble(getSize())-(getSize()/2));
			yPoints[i] = (int) ((Math.sin((2 * Math.PI) / nbrOfCorners * i) * getSize()) + rand.nextDouble(getSize())-(getSize()/2));
			//System.out.println((Math.cos((2 * Math.PI) / nbrOfCorners * i) * getSize()));
		}
		
		//System.out.print(yPoints[5]);
	}
	
	@Override
	public void draw(Graphics g) {
		int[] newXPoints = new int[nbrOfCorners];
		int[] newYPoints = new int[nbrOfCorners];
		
		g.setColor(Color.white);

		for(int i = 0; i < nbrOfCorners; i++) {
			newXPoints[i] = xPoints[i] + (int)getPosition().getX();
			newYPoints[i] = yPoints[i] + (int)getPosition().getY();
		}
		
		Polygon shape = new Polygon(newXPoints, newYPoints, nbrOfCorners);
		g.drawPolygon(shape);

	}



}
