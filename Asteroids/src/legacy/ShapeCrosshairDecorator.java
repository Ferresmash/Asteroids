package legacy;

import java.awt.Color;
import java.awt.Graphics;

public class ShapeCrosshairDecorator implements Shape {
	private Shape decoratee;

	public ShapeCrosshairDecorator(Shape decoratee) {
		this.decoratee = decoratee;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(new Color(0,0,0));
		decoratee.draw(g);
		Point position = decoratee.getPosition();
		int x = (int) (position.getX() + 0.5);
		int y = (int) (position.getY() + 0.5);

		g.setColor(new Color(255,0,0));
		g.drawLine((int) (x - decoratee.getWidth() / 2), (int) (y),
				(int) (x + decoratee.getWidth() / 2)-1, (int) (y));
		g.drawLine((int) (x), (int) (y - decoratee.getHeight() / 2),
				(int) (x), (int) (y + decoratee.getHeight() / 2));
		g.setColor(new Color(0,0,0));
		//g.drawLine(x - 1, y - 5, 2, 10);
	}
	
	@Override
	public void fill(Graphics g) {
		decoratee.fill(g);
	}

	@Override
	public Point getPosition() {
		return decoratee.getPosition();
	}

	@Override
	public double getWidth() {
		return decoratee.getWidth();
	}

	@Override
	public double getHeight() {
		return decoratee.getHeight();
	}

	@Override
	public boolean intersects(Point point) {
		return decoratee.intersects(point);
	}

	@Override
	public void moveTo(Point point) {
		decoratee.moveTo(point);
	}

	@Override
	public void move(double dx, double dy) {
		decoratee.move(dx, dy);
	}

	@Override
	public void resizeTo(Point point) {
		decoratee.resizeTo(point);
	}

	@Override
	public Shape peel() {
		return decoratee;
	}
}