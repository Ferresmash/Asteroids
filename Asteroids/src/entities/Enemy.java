package entities;

import pos.Force;
import pos.Position;

public abstract class Enemy implements Entity{

	private double size;
	private Position position;
	private Force force;
	
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
	
	
}
