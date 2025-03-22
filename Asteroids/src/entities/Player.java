package entities;

import pos.Force;
import pos.Position;
import view.RenderVisitor;


public class Player extends GameObject {


	private boolean isAccelerating = false;
	private int lifes = 5;
	private double speed = 0.3;

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
		setForce(getForce().getX() * 0.97, getForce().getY() * 0.97);
	}

	@Override
	public void accept(RenderVisitor visitor) {
		visitor.visit(this);
	}


	public void getHit() {
		//lose life
		//get destroyed
		//respawn if life left
	}

	public boolean isAccelerating() {
		return isAccelerating;
	}

	public void setAccelerating(boolean isAccelerating) {
		this.isAccelerating = isAccelerating;
	}

	public int getLifes() {
		return lifes;
	}

	public void setLifes(int lifes) {
		this.lifes = lifes;
	}



}
