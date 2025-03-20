package legacy;

import java.awt.Shape;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

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
		List<Enemy> enemies = getEnemyHandler().getEnemies();
		List<Enemy> toBeRemoved = new ArrayList<Enemy>();
		int length = enemies.size();
		for (int i = length-1; i > 0; i--) {
			Shape polygon = enemies.get(i).getHitbox();
			for (Bullet b : getBullets()) {
				boolean hit = false;
				if (polygon.contains(b.getX(), b.getY())) {
					System.out.println(enemies.size());
					enemies.get(i).destroy(this);
					if(!toBeRemoved.contains(enemies.get(i))) {
						toBeRemoved.add(enemies.get(i));
					}
					hit = true;
					System.out.println(enemies.size());
				}
				if(hit) break;
			}
		}
		
		SwingUtilities.invokeLater(() -> {
			for(Enemy enemy : toBeRemoved) {
				getEnemyHandler().remove(enemy);
			}
		});
		
	}
}
