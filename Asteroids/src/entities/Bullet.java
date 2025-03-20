package entities;

import java.awt.Color;
import java.awt.Graphics;
import pos.Position;

public class Bullet implements Entity{
	
	private double speed = 15.0d;
	private Position position = new Position(0,0);
	private double angle = 0d;
	
	public Bullet(Position position, double angle) {
		this.position = position;
		this.angle = angle;
	}
	
	public void move() {
		setPosX((int) (position.getX() + speed*Math.cos(angle)));
		setPosY((int) (position.getY() + speed*Math.sin(angle)));
	}
	
	public void setPos(Position pos) {
		position.setX(pos.getX());
		position.setY(pos.getY());
	}
	
	public double getX() {
		return position.getX();
	}
	
	public void setPosX(int x) {
		position.setX(x);
	}
	
	public double getY() {
		return position.getY();
	}
	
	public void setPosY(int y) {
		position.setY(y);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.white);
	    g.fillOval((int) getX(), (int) getY(), 5, 5);
	}

}
