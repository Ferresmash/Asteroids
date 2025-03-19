package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import pos.Force;
import pos.Position;

public class Player {
	
	private double speed = 1d;
	private Position position = new Position(300,300);
	public double angle = 0d;
	private Force force = new Force();
	
	public Player() {
		
	}
	
	public void accelerate() {
		speed *= 1.1;
	}
	
	public void move() {
		double[] direction = {speed*Math.cos(angle), speed*Math.cos(angle)};
		force.add(direction);
		
		setPosX((int) (position.getX() + force.getX()));
		setPosY((int) (position.getY() + force.getY()));
		if(speed > 1) {
			speed *= 0.95;
		}
		
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
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
	    g2d.setColor(Color.WHITE);
	    Rectangle rect2 = new Rectangle(position.getX()-10, position.getX()-20, 20, 40);

	    g2d.rotate(angle);
	    g2d.draw(rect2);
	    g2d.fill(rect2);
	}
	
	
	

}
