package entities;

import pos.Force;
import pos.Position;
import view.RenderVisitor;


public class Bullet extends GameObject{

	public Bullet(Position position, Force force) {
		setPosition(position);
		setForce(force);
	}
	
	public void move() {
		super.setPosition(getPosition().getX() + getForce().getX(), getPosition().getY() - getForce().getY());
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
