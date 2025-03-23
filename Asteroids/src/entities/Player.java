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
	private final Position START_POINT;
	private final double FRICTION = 0.99;
	private final int HITBOXHEIGHT = 20;
	private final int HITBOXWIDTH = 10;

	public Player(Position startPos) {
		START_POINT = startPos;
		setPosition(START_POINT);
		setForce(new Force(0, 0));
	}
	
	public void accelerate() {
		setAccelerating(true);
		double[] direction = { speed * Math.cos(angle), speed * Math.sin(angle)};
		force.add(direction);
	}

	public void move() {
		setPosition(position.getX() + force.getX(), position.getY() - force.getY());
		setForce(force.getX() * FRICTION, force.getY() * FRICTION);
	}

	@Override
	public void accept(RenderVisitor visitor) {
		visitor.visit(this);
	}
	
	
	public Shape getHitbox() {
		return new Rectangle((int)position.getX()-(HITBOXWIDTH/2),(int)position.getY()-(HITBOXHEIGHT/2),HITBOXWIDTH,HITBOXHEIGHT);
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
	
	public void rotateRight() {
		rotate(rotationSpeed);
	}
	
	public void rotateLeft() {
		rotate(-rotationSpeed);
	}

}
