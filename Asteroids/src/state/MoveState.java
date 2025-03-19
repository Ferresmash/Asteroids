package state;

import legacy.GameContainer;

public class MoveState implements State {

	@Override
	public void handle(GameContainer shapeContainer) {
		if(shapeContainer.getSelected() != null) {
			shapeContainer.getSelected().moveTo(shapeContainer.getPoint());
		}
	}

}
