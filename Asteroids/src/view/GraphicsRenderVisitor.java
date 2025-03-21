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
		
		int xPos = (int) player.getPosition().getX();
		int yPos = (int) player.getPosition().getY();

		int[] xPoints = { xPos - 20, xPos - 20, xPos + 20 };
		int[] yPoints = { yPos - 10, yPos + 10, yPos };
		Polygon rocket = new Polygon(xPoints, yPoints, 3);
		int[] xPointsWings = { xPos - 20,xPos - 20, xPos + 20 };
		int[] yPointsWings = {yPos - 15,yPos + 15, yPos };
		Polygon wings = new Polygon(xPointsWings, yPointsWings, 3);
		int[] xPointsWindow = {xPos - 8,xPos - 8,xPos + 8 };
		int[] yPointsWindow = {yPos + 4,yPos - 4,yPos };
		Polygon window = new Polygon(xPointsWindow, yPointsWindow, 3);
		g2d.rotate(-player.getAngle(), xPos, yPos);
		g2d.setColor(new Color(100,100,100));
		g2d.fill(wings);
		g2d.setColor(Color.lightGray);
		g2d.fill(rocket);
		g2d.setColor(new Color(75,75,75));
		g2d.fill(window);
		if(player.isAccelerating()) {
			int[] xPointsFlame = {xPos - 20,xPos - 20,xPos -38 };
			int[] yPointsFlame = {yPos - 8,yPos + 8,yPos };

			Polygon flame = new Polygon(xPointsFlame, yPointsFlame, 3);
			g2d.setColor(Color.red);
			g2d.fill(flame);
			int[] xPointsSmallFlame = { xPos - 20,xPos - 20, xPos -34 };
			int[] yPointsSmallFlame = { yPos - 4, yPos + 4, yPos };

			Polygon smallFlame = new Polygon(xPointsSmallFlame, yPointsSmallFlame, 3);
			g2d.setColor(Color.yellow);
			g2d.fill(smallFlame);
		}
		g2d.rotate(player.getAngle(), xPos, yPos);

	}

	@Override
	public void visit(Bullet bullet) {
		Graphics2D g2d = (Graphics2D) g;
		
		int x = (int) bullet.getPosition().getX();
		int y = (int) bullet.getPosition().getY();
		
		g2d.rotate(-bullet.getForce().getAngle(), x, y);

		g2d.setColor(Color.yellow);
		g2d.fillRect((int) x, (int) y, 10, 2);
		
		g2d.rotate(bullet.getForce().getAngle(), x, y);
	}

}
