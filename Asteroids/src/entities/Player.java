package entities;

import java.awt.Rectangle;
import java.awt.Shape;

import legacy.GameManager;
import pos.Force;
import pos.Position;
import view.RenderVisitor;


public class Player extends GameObject {


	private boolean isAccelerating = false;
	private double speed = 0.1;
	private final Position START_POINT = new Position(500,500);
	private final double FRICTION = 0.99;

	public Player() {
		setPosition(new Position(300, 300));
		setForce(new Force(0, 0));
	}
	
	public void accelerate() {
		setAccelerating(true);
		double[] direction = { speed * Math.cos(getAngle()), speed * Math.sin(getAngle()) };
		getForce().add(direction);
	}

	public void move() {
		setPosition(getPosition().getX() + getForce().getX(), getPosition().getY() - getForce().getY());
		setForce(getForce().getX() * FRICTION, getForce().getY() * FRICTION);
	}

	@Override
	public void accept(RenderVisitor visitor) {
		visitor.visit(this);
	}
	
	
	public Shape getHitbox() {
		return new Rectangle((int)getPosition().getX(),(int)getPosition().getY(),5,5);
	}


	public void getHit() {
		GameManager.getInstance().decreaseLives();
		System.out.println(GameManager.getInstance().getLives());
		setPosition(START_POINT);
	}

	public boolean isAccelerating() {
		return isAccelerating;
	}

	public void setAccelerating(boolean isAccelerating) {
		this.isAccelerating = isAccelerating;
	}

}
