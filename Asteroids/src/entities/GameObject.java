package entities;

import java.awt.Rectangle;
import java.awt.Shape;
import java.util.List;

import pos.Force;
import pos.Position;

public abstract class GameObject implements Drawable {

	private Position position;
	private Force force;
	private double angle = 0;

	private int screenWidth;
	private int screenHeight;
	
	
	
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	public void setPosition(double x, double y) {
		this.position = new Position(x,y);
	}
	
	public Force getForce() {
		return force;
	}

	public void setForce(Force force) {
		this.force = force;
	}
	
	public void setForce(double x, double y) {
		this.force = new Force(x,y);
	}
	
	public Shape getHitbox() {
		return new Rectangle((int)getPosition().getX(),(int)getPosition().getY(),1,1);
	}
	
	public void getHit(List<GameObject> gameObjects) { //in asteroid add itself to the list
		gameObjects.remove(this);
	};
	
	public void getHit() {
		
	}
	
	public void move() {
		System.out.println("Implement move for specific gameobject");
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
	
	public void setScreenSize(int width, int heigth) {
		this.setScreenHeight(heigth);
		this.setScreenWidth(width);
	}

	public int getScreenHeight() {
		return screenHeight;
	}

	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}


}
