package entities;

import java.awt.Graphics;

import pos.Force;
import pos.Position;

public class UFO extends Enemy {
	
	
	
	public UFO(Position spawnPos, Force spawnForce) {
		setForce(spawnForce);
		setPosition(spawnPos);
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub

	}

}
