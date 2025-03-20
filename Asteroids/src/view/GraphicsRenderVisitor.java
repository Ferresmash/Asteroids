package view;

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
        // Draw UFO polygons here...
    }
    
    @Override
    public void visit(Asteroid asteroid) {
        // Drawing logic for Asteroid
    }
    
    @Override
    public void visit(Player player) {
        // Drawing logic for Player
    }
    
    @Override
    public void visit(Bullet bullet) {
        // Drawing logic for Bullet
    }

}
