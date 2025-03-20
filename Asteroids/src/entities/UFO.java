package entities;


import legacy.EntityHandler;
import pos.Force;
import pos.Position;
import view.RenderVisitor;

public class UFO extends Enemy implements GameObject {
	
	
	public UFO() {
		this(new Position(600, 300), new Force());
	}
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
	public void destroy(EntityHandler entityHandler) {
		// TODO Auto-generated method stub
		
	}


}
