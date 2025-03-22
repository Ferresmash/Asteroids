package entities;
import java.awt.Rectangle;
import java.awt.Shape;

public abstract class Enemy extends GameObject{

	private double size;


	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public void move() {
		getPosition().setX((getPosition().getX() + getForce().getX()));
		getPosition().setY((getPosition().getY() + getForce().getY()));
		;
	}

	
	@Override
	public Shape getHitbox() {
		System.out.println("enemy hitbox");
		return new Rectangle((int) (getPosition().getX()-size/2), (int) (getPosition().getY()-size/2),(int)size, (int)size);
	}



}
