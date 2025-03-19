package entities;

import pos.Position;

public class Player {
	
	private double speed = 1.0d;
	private Position position = new Position();
	private double direction = 0d;
	
	public Player() {
		
	}
	
	public void move() {
		setPosX((int) (position.getX()+speed*Math.cos(direction)));
		setPosY((int) (position.getX()+speed*Math.sin(direction)));
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
