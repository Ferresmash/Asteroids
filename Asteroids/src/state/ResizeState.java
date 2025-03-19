package state;

import legacy.ShapeContainer;

public class ResizeState implements State {

	@Override
	public void handle(ShapeContainer shapeContainer) {
		if(shapeContainer.getSelected() != null) {
			shapeContainer.getSelected().resizeTo(shapeContainer.getPoint());
		}
	}

}
