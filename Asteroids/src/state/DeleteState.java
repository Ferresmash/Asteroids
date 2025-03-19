package state;

import legacy.GameContainer;

public class DeleteState implements State {

	@Override
	public void handle(GameContainer shapeContainer) {
		shapeContainer.select(shapeContainer.getPoint());
		if(shapeContainer.getSelected() != null) {
			shapeContainer.getShapes().remove(shapeContainer.getSelected());
		}
		shapeContainer.setSelected(null);
	}

}
