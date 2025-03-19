package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import pos.Force;
import pos.Position;

public class UFO extends Enemy {
	
	private int[] baseX = {50, 100, 150};
    private int[] baseY = {100, 150, 100};
    private int[] cockpitX = {75, 100, 125};
    private int[] cockpitY = {50, 75, 50};
    private int[] baseLineX = {50, 150};
    private int[] baseLineY = {100, 100};
	
	public UFO() {
		this(new Position(600, 300), new Force());
	}
	public UFO(Position spawnPos, Force spawnForce) {
		setForce(spawnForce);
		setPosition(spawnPos);
	}
	


	@Override
	public void draw(Graphics g) {
		int startx = getPosition().getX();
		int starty = getPosition().getY();
		
		g.setColor(Color.green);
		
        Polygon topBase = new Polygon();
        topBase.addPoint(startx + 2*4, starty+ 2*2);
        topBase.addPoint(startx + 5*4, starty+ 7*2);
        topBase.addPoint(startx + -5*4, starty+ 7*2);
        topBase.addPoint(startx + -2*4, starty+ 2*2);
        
        Polygon lowBase = new Polygon();
        lowBase.addPoint(startx + 2*4, starty+ 10*2);
        lowBase.addPoint(startx + 5*4, starty+ 5*2);
        lowBase.addPoint(startx + -5*4, starty+ 5*2);
        lowBase.addPoint(startx + -2*4, starty+ 10*2);
        
        // Cockpit (top trapezoid)
        Polygon cockpit = new Polygon();
        cockpit.addPoint(startx + 3, starty+ -3);
        cockpit.addPoint(startx + 8, starty+ 5);
        cockpit.addPoint(startx + -8, starty+ 5);
        cockpit.addPoint(startx + -3, starty+ -3);
        
        g.drawPolygon(topBase);
        g.drawPolygon(cockpit);
        g.drawPolygon(lowBase);

        g.setColor(Color.white);
//        Polygon base = new Polygon(baseX, baseY, baseX.length);
//        Polygon cockpit = new Polygon(cockpitX, cockpitY, cockpitX.length);
//        Polygon baseLine = new Polygon(baseLineX, baseLineY, baseLineX.length);
//        g.drawPolygon(base);
//        g.drawPolygon(cockpit);
//        g.drawPolyline(baseLineX, baseLineY, baseLineX.length);

	}

}
