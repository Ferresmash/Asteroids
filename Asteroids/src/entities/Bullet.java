package entities;

import pos.Position;
import view.RenderVisitor;


public class Bullet implements GameObject, Drawable{
	
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
	
	public int getX() {
		return (int) position.getX();
	}
	
	public void setPosX(int x) {
		position.setX(x);
	}
	
	public int getY() {
		return (int) position.getY();
	}
	
	public void setPosY(int y) {
		position.setY(y);
	}


	@Override
	public void accept(RenderVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
