package shapeState;

import legacy.Circle;
import legacy.Point;
import legacy.Shape;

public class CircleState implements ShapeState {

	@Override
	public Shape getShape(Point point) {
		return new Circle(point, Math.random() * 25.0+25);
	}

}
