package legacy;

import java.awt.Shape;
import java.util.ArrayList;
import java.util.List;

import entities.Bullet;
import entities.Drawable;
import entities.Enemy;
import entities.Player;
import entities.UFO;
import pos.Force;
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
		bullets.add(new Bullet(new Position(player.getPosition().getX(), player.getPosition().getY()), new Force(Math.cos(-player.getAngle()) * 10,Math.sin(player.getAngle()) * 10)));
	}
	
//	public void addEnemyBullet(UFO ufo) {
//		bullets.add(new Bullet(new Position(ufo.getPosition().getX(), ufo.getPosition().getY()), -player.getAngle()));
//	}

	public void checkCollision() {
		for (Enemy enemy : getEnemyHandler().getEnemies()) {
			Shape polygon = enemy.getHitbox();
			for (Bullet bullet : getBullets()) {
				if (polygon.contains(bullet.getPosition().getX(), bullet.getPosition().getY())) {
					//enemy.destroy();
					//getEnemyHandler().remove(enemy);
				}
			}
		}		
	}
}
