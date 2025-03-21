package entities;


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
	public void accept(RenderVisitor visitor) {
		visitor.visit(this);		

	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}


}
