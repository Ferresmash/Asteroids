package legacy;

import java.util.ArrayList;
import java.util.List;

import entities.Drawable;
import entities.Player;

public class EntityHandler {

	List<Drawable> bullets = new ArrayList<Drawable>();
	List<Drawable> enemyBullets = new ArrayList<Drawable>();
	EnemyHandler enemyHandler = new EnemyHandler();
	Player player = new Player();
	
	public EntityHandler() {
		
	}

	public List<Drawable> getEntities() {
		List<Drawable> allEntities = new ArrayList<Drawable>();
		for (Drawable drawable : enemyHandler.getEnemies()) {
			allEntities.add(drawable);
		}
		for (Drawable drawable : bullets) {
			allEntities.add(drawable);
		}
		for (Drawable drawable : enemyBullets) {
			allEntities.add(drawable);
		}
		allEntities.add(player);
		return allEntities;
	}
	
	public EnemyHandler getEnemyHandler() {
		return enemyHandler;
	}

	public Player getPlayer() {
		return player;
	}
}
