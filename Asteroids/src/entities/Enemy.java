package entities;
import java.awt.Rectangle;
import java.awt.Shape;

import pos.Force;
import pos.Position;

public abstract class Enemy implements Entity {

	private double size;
	private Position position;
	private Force force;
	private int screenWidth;
	private int screenHeight;

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Force getForce() {
		return force;
	}

	public void setForce(Force force) {
		this.force = force;
	}

	public void move() {
		keepInside();
		getPosition().setX((int) (getPosition().getX() + getForce().getX()));
		getPosition().setY((int) (getPosition().getY() + getForce().getY()));
	}

	public void keepInside() {
		if(getPosition().getX() < -20) {
			getPosition().setX(screenWidth);
        }
        if(getPosition().getX() > screenWidth) {
        	getPosition().setX(-20);
        }
        if(getPosition().getY() < -20) {
        	getPosition().setY(screenHeight);
        }
        if(getPosition().getY() > screenHeight) {
        	getPosition().setY(-20);
        }
	}
	
	public Shape getHitbox() {
		return new Rectangle((int) (getPosition().getX()-size/2), (int) (getPosition().getY()-size/2),(int)size, (int)size);
	}

	public void setScreenSize(int width, int heigth) {
		this.screenHeight = heigth;
		this.screenWidth = width;
	}

}
