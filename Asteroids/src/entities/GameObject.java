package entities;

import java.awt.Rectangle;
import java.awt.Shape;
import java.util.List;

import pos.Force;
import pos.Position;

public abstract class GameObject implements Drawable {

	protected Position position;
	protected Force force;
	protected double angle = 0;
	protected double size = 100d;
	
	public Position getPosition() {
		return new Position(position.getX(),position.getY());
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	public void setPosition(double x, double y) {
		this.position = new Position(x,y);
	}
	
	public Force getForce() {
		return new Force(force.getX(),force.getY());
	}

	public void setForce(Force force) {
		this.force = force;
	}
	
	public void setForce(double x, double y) {
		this.force = new Force(x,y);
	}
	
	public Shape getHitbox() {
		return new Rectangle((int)getPosition().getX(),(int)getPosition().getY(),2,2);
	}
	
	public void getHit(List<GameObject> gameObjects) {
		gameObjects.remove(this);
	};
	
	public void getHit() {
		
	}
	
	public void move() {
		setPosition(getPosition().getX() + getForce().getX(), getPosition().getY() + getForce().getY());
	}
	
	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	public void rotate(double angle) {
		this.angle += angle;
	}
	
	public void setSize(double size) {
		this.size = size;
	}



}
