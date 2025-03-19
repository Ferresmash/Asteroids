package state;

import legacy.ShapeContainer;

public class DeleteState implements State {

	@Override
	public void handle(ShapeContainer shapeContainer) {
		shapeContainer.select(shapeContainer.getPoint());
		if(shapeContainer.getSelected() != null) {
			shapeContainer.getShapes().remove(shapeContainer.getSelected());
		}
		shapeContainer.setSelected(null);
	}

}
