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
		g.setColor(new Color(0,100,0));
		g.fillPolygon(cockpit);
		g.setColor(new Color(0,100,0));
		g.fillPolygon(lowBase);
		g.setColor(new Color(0,200,0));
		g.fillPolygon(topBase);
		
		
		

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

		for (int i = 0; i < asteroid.getNbrOfCorners(); i++) {
			newXPoints[i] = asteroid.getxPoints()[i] + (int) asteroid.getPosition().getX();
			newYPoints[i] = asteroid.getyPoints()[i] + (int) asteroid.getPosition().getY()+5;
		}

		Polygon shade = new Polygon(newXPoints, newYPoints, asteroid.getNbrOfCorners());
		
		g.setColor(new Color(92, 65, 0));
		g.fillPolygon(shade);
		g.setColor(new Color(137, 96, 0));
		g.fillPolygon(shape);
		
	}

	@Override
	public void visit(Player player) {
		Graphics2D g2d = (Graphics2D) g;
		

		int[] xPoints = { player.getX() - 20, player.getX() - 20, player.getX() + 20 };
		int[] yPoints = { player.getY() - 10, player.getY() + 10, player.getY() };
		Polygon rocket = new Polygon(xPoints, yPoints, 3);
		int[] xPointsWings = { player.getX() - 20, player.getX() - 20, player.getX() + 20 };
		int[] yPointsWings = { player.getY() - 15, player.getY() + 15, player.getY() };
		Polygon wings = new Polygon(xPointsWings, yPointsWings, 3);
		int[] xPointsWindow = {  player.getX() - 8,player.getX() - 8, player.getX() + 8 };
		int[] yPointsWindow = { player.getY() + 4, player.getY() - 4, player.getY() };
		Polygon window = new Polygon(xPointsWindow, yPointsWindow, 3);
		g2d.rotate(-player.angle, player.getX(), player.getY());
		g2d.setColor(new Color(100,100,100));
		g2d.fill(wings);
		g2d.setColor(Color.lightGray);
		g2d.fill(rocket);
		g2d.setColor(new Color(75,75,75));
		g2d.fill(window);
		if(player.isAccelerating()) {
			int[] xPointsFlame = { player.getX() - 20, player.getX() - 20, player.getX() -38 };
			int[] yPointsFlame = { player.getY() - 8, player.getY() + 8, player.getY() };

			Polygon flame = new Polygon(xPointsFlame, yPointsFlame, 3);
			g2d.setColor(Color.red);
			g2d.fill(flame);
			int[] xPointsSmallFlame = { player.getX() - 20, player.getX() - 20, player.getX() -34 };
			int[] yPointsSmallFlame = { player.getY() - 4, player.getY() + 4, player.getY() };

			Polygon smallFlame = new Polygon(xPointsSmallFlame, yPointsSmallFlame, 3);
			g2d.setColor(Color.yellow);
			g2d.fill(smallFlame);
		}
	}

	@Override
	public void visit(Bullet bullet) {
		Graphics2D g2d = (Graphics2D) g;

		g2d.rotate(bullet.getAngle(), bullet.getX(), bullet.getY());

		g2d.setColor(Color.yellow);
		g2d.fillRect((int) bullet.getX(), (int) bullet.getY(), 10, 2);
		
		g2d.rotate(-bullet.getAngle(), bullet.getX(), bullet.getY());

	}

}
