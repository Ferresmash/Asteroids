package entities;



import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.List;

import legacy.GameManager;
import pos.Force;
import pos.Position;
import view.RenderVisitor;

public class UFO extends Enemy {
	
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
	public void getHit(List<GameObject> gameObjects) { //in asteroid add itself to the list
		GameManager.getInstance().increaseScore(1000);
		gameObjects.remove(this);
	};
	
	@Override
	public void accept(RenderVisitor visitor) {
		visitor.visit(this);		

	}

}
