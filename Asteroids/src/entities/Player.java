package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import pos.Force;
import pos.Position;

public class Player implements Entity{
	
	private double speed = 0.1d;
	private Position position = new Position(300,300);
	public double angle = 0d;
	private Force force = new Force();
	
	public Player() {
		
	}
	
	public void accelerate() {
		speed *= 1.01;
		double[] direction = {speed*Math.cos(angle), speed*Math.sin(angle)};
		force.add(direction);
	}
	
	public void move() {
		
		
		setPosX((int) (position.getX() + force.getX()));
		setPosY((int) (position.getY() - force.getY()));

		//force.setX(force.getX()*0.95);
		//force.setY(force.getY()*0.95);
		
	}
	
	public void setPos(Position pos) {
		setPosX((int) pos.getX());
		setPosY((int) pos.getY());
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
	    Rectangle rect2 = new Rectangle((int)position.getX()-10, (int)position.getY()-20, 20, 40);

	    g2d.rotate(angle, position.getX(), position.getY());
	    g2d.draw(rect2);
	    g2d.fill(rect2);
	}
	
	
	

}
