package legacy;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

import shapeState.CircleState;
import shapeState.ShapeState;
import state.InsertState;
import state.State;
import decoratorState.*;

public class ShapeContainer extends JPanel implements Pointable {
	private static final long serialVersionUID = 1L;
	private List<Shape> shapes = new LinkedList<Shape>();
	public List<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(List<Shape> shapes) {
		this.shapes = shapes;
	}

	private Point point;
	private State state = new InsertState();
	private ShapeState shapeState = new CircleState();
	private Shape selected;
	private DecoratorState decoratorState = new FillState();

	public void setDecoratorState(DecoratorState decoratorState) {
		this.decoratorState = decoratorState;
	}

	public Shape getSelected() {
		return selected;
	}

	public void setSelected(Shape selected) {
		this.selected = selected;
	}

	public ShapeContainer() {
		super();
		MouseHandler mouseHandler = new MouseHandler(this);
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
		this.setBackground(Color.white);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Shape shape : shapes)
			shape.draw(g);
	}
  

	public void select(Point point) {
		for (Shape shape : shapes) {
			if (shape.intersects(point)) {
				selected = shape;
				return;
			}
		}
	}

	public void pointerDown(Point point) {
		this.point = point;
		select(point);
		state.handle(this);
		repaint();
	}

	public void pointerUp(Point point) {
		selected = null;
	}

	public void pointerMoved(Point point, boolean pointerDown) {
		if (selected != null && pointerDown) {
			this.point = point;
			state.handle(this);
			repaint();
		}
	}

	public State getState() {
		return state;
	}

	public void setState(State currState) {
		this.state = currState;
	}
	public ShapeState getShapeState() {
		return shapeState;
	}

	public void setShapeState(ShapeState shapeState) {
		this.shapeState = shapeState;
	}
	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public DecoratorState getDecoratorState() {
		return this.decoratorState;
	}

}
