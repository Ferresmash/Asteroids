package entities;



import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import pos.Force;
import pos.Position;
import view.RenderVisitor;

public class UFO extends Enemy {
	
//	Timer shootTimer = new Timer();
//	Timer changeDirection = new Timer();
	
	
	public UFO(Position spawnPos, Force spawnForce) {
		setSize(100);
		setForce(spawnForce);
		setPosition(spawnPos);
		
	}
	
	@Override
	public Shape getHitbox() {
		return new Ellipse2D.Double((int)getPosition().getX()-20,(int)getPosition().getY()-10,40,40);
		
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
