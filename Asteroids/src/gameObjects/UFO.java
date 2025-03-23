package gameObjects;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import pos.Force;
import pos.Position;
import view.RenderVisitor;

public class UFO extends GameObject {

	public UFO(Position spawnPos, Force spawnForce, double size) {
		this(spawnPos, spawnForce);
		setSize(size);
	}

	public UFO(Position spawnPos, Force spawnForce) {
		setForce(spawnForce);
		setPosition(spawnPos);
	}

	@Override
	public Shape getHitbox() {
		return new Ellipse2D.Double((int) getPosition().getX() - 20, (int) getPosition().getY() - 10, 40, 40);
	}

	@Override
	public void accept(RenderVisitor visitor) {
		visitor.visit(this);

	}

}
