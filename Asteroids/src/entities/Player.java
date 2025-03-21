package entities;

import legacy.EntityHandler;
import pos.Force;
import pos.Position;
import view.RenderVisitor;

public class Player implements GameObject, Drawable{
	
	private double speed = 0.1d;
	private Position position = new Position(300,300);
	public double angle = Math.PI/2;


	private Force force = new Force();
	private int screenWidth = 1000;
	private int screenHeight = 700;
	
	public Player() {
		
	}
	
	public void accelerate() {
		double[] direction = {speed*Math.cos(angle), speed*Math.sin(angle)};
		force.add(direction);
	}
	
	public void move() {
		
		//double[] direction = {speed/2*Math.cos(angle+Math.PI), speed/2*Math.sin(angle+Math.PI)};
		//force.add(direction);
		
		setX((int) (position.getX() + force.getX()));
		setY((int) (position.getY() - force.getY()));
		
		
		//force.setX(force.getX()*0.95);
		//force.setY(force.getY()*0.95);
		keepInside();
	}
	
	public void keepInside() {
        if(getX() < -20) {
            setX(screenWidth);
        }
        if(getX() > screenWidth) {
            setX(-20);
        }
        if(getY() < -20) {
            setY(screenHeight);
        }
        if(getY() > screenHeight) {
            setY(-20);
        }
    }
	
	public void setPos(Position pos) {
		setX((int) pos.getX());
		setY((int) pos.getY());
	}
	
	public Position getPos() {
		return position;
	}
	
	public int getX() {
		return (int)position.getX();
	}
	
	public void setX(int x) {
		position.setX(x);
	}
	
	public int getY() {
		return (int)position.getY();
	}
	
	public void setY(int y) {
		position.setY(y);
	}
	


	public void setScreenSize(int width, int heigth) {
		screenWidth = width;
		screenHeight = heigth;
	}
	
	@Override
	public void accept(RenderVisitor visitor) {
		visitor.visit(this);		
	}
	
	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	@Override
	public void destroy(EntityHandler entityHandler) {
		// TODO Auto-generated method stub
		
	}

}
