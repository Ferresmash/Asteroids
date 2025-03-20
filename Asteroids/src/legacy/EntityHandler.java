package legacy;

import java.awt.Shape;
import java.util.ArrayList;
import java.util.List;

import entities.Bullet;
import entities.Drawable;
import entities.Enemy;
import entities.Player;
import pos.Position;

public class EntityHandler {

	List<Bullet> bullets = new ArrayList<Bullet>();
	List<Bullet> enemyBullets = new ArrayList<Bullet>();
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
	
	public List<Bullet> getBullets() {
		return bullets;
	}

	public void setBullets(List<Bullet> bullets) {
		this.bullets = bullets;
	}

	public List<Bullet> getEnemyBullets() {
		return enemyBullets;
	}

	public void setEnemyBullets(List<Bullet> enemyBullets) {
		this.enemyBullets = enemyBullets;
	}

	public void addBullet() {
		bullets.add(new Bullet(new Position(player.getX(), player.getY()), -player.getAngle()));
	}

	public void checkCollision() {
		for (Enemy enemy : getEnemyHandler().getEnemies()) {
			Shape polygon = enemy.getHitbox();
			for (Bullet b : getBullets()) {
				if (polygon.contains(b.getX(), b.getY())) {
					//enemy.destroy();
					//getEnemyHandler().remove(enemy);
				}
			}
		}		
	}
}
