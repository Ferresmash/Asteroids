package view;

import gameObjects.Asteroid;
import gameObjects.Bullet;
import gameObjects.Player;
import gameObjects.UFO;

public interface RenderVisitor {

    void visit(UFO ufo);
    void visit(Asteroid asteroid);
    void visit(Player player);
    void visit(Bullet bullet);
	
}
