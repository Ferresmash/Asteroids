package entities;
import java.awt.Rectangle;
import java.awt.Shape;

import pos.Force;
import pos.Position;

public abstract class Enemy extends GameObject{

	private double size;


	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public void move() {
		keepInside();
		getPosition().setX((int) (getPosition().getX() + getForce().getX()));
		getPosition().setY((int) (getPosition().getY() + getForce().getY()));
	}

	public void keepInside() {
		if(getPosition().getX() < -20) {
			getPosition().setX(getScreenWidth());
        }
        if(getPosition().getX() > getScreenWidth()) {
        	getPosition().setX(-20);
        }
        if(getPosition().getY() < -20) {
        	getPosition().setY(getScreenHeight());
        }
        if(getPosition().getY() > getScreenHeight()) {
        	getPosition().setY(-20);
        }
	}
	
	@Override
	public Shape getHitbox() {
		System.out.println("enemy hitbox");
		return new Rectangle((int) (getPosition().getX()-size/2), (int) (getPosition().getY()-size/2),(int)size, (int)size);
	}



}
