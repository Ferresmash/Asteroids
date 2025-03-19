package state;

import legacy.GameContainer;

public class ResizeState implements State {

	@Override
	public void handle(GameContainer shapeContainer) {
		if(shapeContainer.getSelected() != null) {
			shapeContainer.getSelected().resizeTo(shapeContainer.getPoint());
		}
	}

}
