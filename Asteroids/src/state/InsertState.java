package state;

import legacy.GameContainer;

public class InsertState implements State {

	@Override
	public void handle(GameContainer shapeContainer) {
		shapeContainer.getShapes().add(shapeContainer.getShapeState().getShape(shapeContainer.getPoint()));
		shapeContainer.setSelected(null);
	}

}
