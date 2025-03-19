package state;

import legacy.ShapeContainer;

public class UnmarkState implements State {

	@Override
	public void handle(ShapeContainer shapeContainer) {
		shapeContainer.select(shapeContainer.getPoint());
		if(shapeContainer.getSelected() != null) {
			shapeContainer.getShapes().remove(shapeContainer.getSelected());
			shapeContainer.getShapes().add(shapeContainer.getSelected().peel());
		}
		shapeContainer.setSelected(null);
	}
	

}
