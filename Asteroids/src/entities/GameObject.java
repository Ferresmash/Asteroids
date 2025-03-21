package entities;

import pos.Force;
import pos.Position;

public abstract class GameObject implements Drawable {
	
	private Position position;
	private Force force;
	
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
	
	public void rotate(double angle) {
		force.setAngle(force.getAngle()+angle);
	}
	
	public void destroy() {
		
	};

}
