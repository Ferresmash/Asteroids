package entities;

import pos.Force;
import pos.Position;

public class Bullet {
	
	private double speed = 1.0d;
	private Position position = new Position();
	private double angle = 0d;
	private Force force = new Force();
	
	public Bullet() {
		
	}
	
	public void move() {
		double[] direction = {speed*Math.cos(angle), speed*Math.cos(angle)};
		force.add(direction);
		
		setPosX((int) (position.getX() + force.getX()));
		setPosY((int) (position.getY() + force.getY()));
		speed *= 0.95;
	}
	
	public void setPos(Position pos) {
		position.setX(pos.getX());
		position.setY(pos.getY());
	}
	
	public void setPosX(int x) {
		position.setX(x);
	}
	
	public void setPosY(int y) {
		position.setY(y);
	}

}
