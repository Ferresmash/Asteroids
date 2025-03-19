package decoratorState;

import legacy.Shape;
import legacy.ShapeCrosshairDecorator;

public class CrosshairState implements DecoratorState {

	@Override
	public Shape getDecorator(Shape shape) {
		return new ShapeCrosshairDecorator(shape);
	}
}
