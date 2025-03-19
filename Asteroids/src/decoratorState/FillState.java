package decoratorState;

import legacy.Shape;
import legacy.ShapeDecorator;

public class FillState implements DecoratorState {

	@Override
	public Shape getDecorator(Shape shape) {
		return new ShapeDecorator(shape);
	}

}
