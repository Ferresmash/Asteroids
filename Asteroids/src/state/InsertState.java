package state;

import legacy.ShapeContainer;

public class InsertState implements State {

	@Override
	public void handle(ShapeContainer shapeContainer) {
		shapeContainer.getShapes().add(shapeContainer.getShapeState().getShape(shapeContainer.getPoint()));
		shapeContainer.setSelected(null);
	}

}
