package state;

import legacy.GameContainer;

public class MarkState implements State {

	@Override
	public void handle(GameContainer shapeContainer) {
		shapeContainer.select(shapeContainer.getPoint());
		if(shapeContainer.getSelected() != null) {
			shapeContainer.getShapes().remove(shapeContainer.getSelected());
			shapeContainer.getShapes().add(shapeContainer.getDecoratorState().getDecorator(shapeContainer.getSelected()));
		}
		shapeContainer.setSelected(null);
	}
}
