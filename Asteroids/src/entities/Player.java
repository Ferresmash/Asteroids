package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;

import pos.Force;
import pos.Position;

public class Player {
	
	private double speed = 1d;
	private Position position = new Position(300,300);
	public double angle = Math.PI/2;
	private Force force = new Force();
	
	public Player() {
		
	}
	
	public void accelerate() {
		if(speed < 0.1)
			speed += 0.0002;
		double[] direction = {speed*Math.cos(angle), speed*Math.sin(angle)};
		force.add(direction);
	}
	
	public void move() {
		
		//double[] direction = {speed/2*Math.cos(angle+Math.PI), speed/2*Math.sin(angle+Math.PI)};
		//force.add(direction);
		
		setX((int) (position.getX() + force.getX()));
		setY((int) (position.getY() - force.getY()));
		
		
		force.setX(force.getX()*0.95);
		force.setY(force.getY()*0.95);
		keepInside();
	}
	
	public void keepInside() {
        if(getX() < -20) {
            setX(820);
        }
        if(getX() > 820) {
            setX(-20);
        }
        if(getY() < -20) {
            setY(620);
        }
        if(getY() > 620) {
            setY(-20);
        }
    }
	
	public void setPos(Position pos) {
		setX(pos.getX());
		setY(pos.getY());
	}
	
	public Position getPos() {
		return position;
	}
	
	public int getX() {
		return position.getX();
	}
	
	public void setX(int x) {
		position.setX(x);
	}
	
	public int getY() {
		return position.getY();
	}
	
	public void setY(int y) {
		position.setY(y);
	}
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		//g.drawLine(getX(), getY(), (int) (getX()+100*Math.cos(angle)), (int) (getY()-100*Math.sin(angle)));
	    g2d.setColor(Color.WHITE);
	    Rectangle rect2 = new Rectangle(getX()-20, getY()-10, 40, 20);
	    int[] xPoints = {getX()-20,getX()-20,getX()+20};
	    int[] yPoints = {getY()-10,getY()+10,getY()};
	    Polygon rocket = new Polygon(xPoints,yPoints,3);
	    
	    g2d.rotate(-angle, getX(), getY());
	    //g2d.draw(rect2);
	    g2d.draw(rocket);
	    g2d.fill(rocket);
	}
	
	
	

}
