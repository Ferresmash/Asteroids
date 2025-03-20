package view;

import entities.Asteroid;
import entities.Bullet;
import entities.Player;
import entities.UFO;

public interface RenderVisitor {

    void visit(UFO ufo);
    void visit(Asteroid asteroid);
    void visit(Player player);
    void visit(Bullet bullet);
	
}
