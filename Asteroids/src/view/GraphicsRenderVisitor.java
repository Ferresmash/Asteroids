package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

import entities.Asteroid;
import entities.Bullet;
import entities.Player;
import entities.UFO;

public class GraphicsRenderVisitor implements RenderVisitor {

	private Graphics g;

	public GraphicsRenderVisitor(Graphics g) {
		this.g = g;
	}

	@Override
	public void visit(UFO ufo) {
		// Drawing logic for UFO
		int startx = (int) ufo.getPosition().getX();
		int starty = (int) ufo.getPosition().getY();

		g.setColor(Color.green);

		Polygon topBase = new Polygon();
		topBase.addPoint(startx + 2 * 4, starty + 2 * 2);
		topBase.addPoint(startx + 5 * 4, starty + 7 * 2);
		topBase.addPoint(startx + -5 * 4, starty + 7 * 2);
		topBase.addPoint(startx + -2 * 4, starty + 2 * 2);

		Polygon lowBase = new Polygon();
		lowBase.addPoint(startx + 2 * 4, starty + 10 * 2);
		lowBase.addPoint(startx + 5 * 4, starty + 5 * 2);
		lowBase.addPoint(startx + -5 * 4, starty + 5 * 2);
		lowBase.addPoint(startx + -2 * 4, starty + 10 * 2);

		// Cockpit (top trapezoid)
		Polygon cockpit = new Polygon();
		cockpit.addPoint(startx + 3, starty + -3);
		cockpit.addPoint(startx + 8, starty + 5);
		cockpit.addPoint(startx + -8, starty + 5);
		cockpit.addPoint(startx + -3, starty + -3);

		g.drawPolygon(topBase);
		g.drawPolygon(cockpit);
		g.drawPolygon(lowBase);

		g.setColor(Color.white);

	}

	@Override
	public void visit(Asteroid asteroid) {
		int[] newXPoints = new int[asteroid.getNbrOfCorners()];
		int[] newYPoints = new int[asteroid.getNbrOfCorners()];

		g.setColor(new Color(137, 96, 0));

		for (int i = 0; i < asteroid.getNbrOfCorners(); i++) {
			newXPoints[i] = asteroid.getxPoints()[i] + (int) asteroid.getPosition().getX();
			newYPoints[i] = asteroid.getyPoints()[i] + (int) asteroid.getPosition().getY();
		}

		Polygon shape = new Polygon(newXPoints, newYPoints, asteroid.getNbrOfCorners());
		g.fillPolygon(shape);
	}

	@Override
	public void visit(Player player) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.WHITE);

		int[] xPoints = { player.getX() - 20, player.getX() - 20, player.getX() + 20 };
		int[] yPoints = { player.getY() - 10, player.getY() + 10, player.getY() };
		Polygon rocket = new Polygon(xPoints, yPoints, 3);

		g2d.rotate(-player.angle, player.getX(), player.getY());
		// g2d.draw(rect2);
		g2d.draw(rocket);
	}

	@Override
	public void visit(Bullet bullet) {
		g.setColor(Color.white);
		g.fillOval(bullet.getX(), bullet.getY(), 5, 5);
	}

}
