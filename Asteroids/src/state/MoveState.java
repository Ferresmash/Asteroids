package state;

import legacy.ShapeContainer;

public class MoveState implements State {

	@Override
	public void handle(ShapeContainer shapeContainer) {
		if(shapeContainer.getSelected() != null) {
			shapeContainer.getSelected().moveTo(shapeContainer.getPoint());
		}
	}

}
