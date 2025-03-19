package state;

import legacy.ShapeContainer;

public interface State {
	
	public void handle(ShapeContainer shapeContainer);
	
}
