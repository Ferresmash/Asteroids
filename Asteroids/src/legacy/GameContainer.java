package legacy;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;
import entities.Entity;
import javax.swing.JPanel;


public class GameContainer extends JPanel {
	private static final long serialVersionUID = 1L;
	private List<Entity> entities = new LinkedList<Entity>();
	
	public List<Entity> getEntities() {
		return entities;
	}

	public void setAsteroids(List<Entity> entities) {
		this.entities = entities;
	}


	public GameContainer() {
		super();

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Entity entity : entities)
			entity.draw(g);
	}

  

}
