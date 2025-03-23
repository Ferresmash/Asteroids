package legacy;

import java.util.ArrayList;
import java.util.List;
import entities.Drawable;
import entities.GameObject;
import entities.GameObjectFactory;
import entities.Player;
import pos.Force;
import pos.Position;

public class EntityHandler {

	private GameObjectFactory gameObjectFactory;
	List<GameObject> bullets = new ArrayList<GameObject>();
	List<GameObject> enemyBullets = new ArrayList<GameObject>();
	EnemyHandler enemyHandler = new EnemyHandler();
	Player player;

	public EntityHandler(int width, int height) {
		this.gameObjectFactory = new GameObjectFactory();
		player = new Player(new Position(width / 2, height / 2));
	}

	public List<Drawable> getEntities() {
		List<Drawable> allEntities = new ArrayList<Drawable>();
		allEntities.addAll(enemyHandler.getEnemies());
		allEntities.addAll(bullets);
		allEntities.addAll(enemyBullets);
		allEntities.add(player);
		return allEntities;

	}

	public List<GameObject> getGameObjects() {
		List<GameObject> gameObjects = new ArrayList<GameObject>();
		gameObjects.addAll(enemyHandler.getEnemies());
		gameObjects.addAll(bullets);
		gameObjects.addAll(enemyBullets);
		gameObjects.add(player);
		return gameObjects;

	}

	public EnemyHandler getEnemyHandler() {
		return enemyHandler;
	}

	public Player getPlayer() {
		return player;
	}

	public List<GameObject> getBullets() {
		return bullets;
	}

	public void setBullets(List<GameObject> bullets) {
		this.bullets = bullets;
	}

	public List<GameObject> getEnemyBullets() {
		return enemyBullets;
	}

	public void setEnemyBullets(List<GameObject> enemyBullets) {
		this.enemyBullets = enemyBullets;
	}

	public void addBullet() {
		bullets.add(
				gameObjectFactory.createBullet(new Position(player.getPosition().getX(), player.getPosition().getY()),
						new Force(Math.cos(-player.getAngle()) * 20, Math.sin(player.getAngle()) * 20)));
	}

	public void addEnemyBullet(GameObject ufo) {
		double speed = 5;
		double angle = Math.atan2(player.getPosition().getX() - ufo.getPosition().getX(),
				player.getPosition().getY() - ufo.getPosition().getY()) - Math.PI / 2;
		Force force = new Force(Math.cos(angle) * speed, Math.sin(angle) * speed);
		enemyBullets.add(gameObjectFactory
				.createBullet(new Position(ufo.getPosition().getX(), ufo.getPosition().getY()), force));
	}

	public void checkAllCollisions() {
		checkCollision(bullets, enemyHandler.getUfos());
		checkCollision(bullets, enemyHandler.getAsteroids());
		checkCollision(enemyBullets, player);
		checkCollision(enemyHandler.getUfos(), player);
		checkCollision(enemyHandler.getAsteroids(), player);
	}

	public void checkCollision(List<GameObject> colliders, List<GameObject> collidedObjects) {
		for (int i = colliders.size() - 1; i >= 0; i--) {
			GameObject collider = colliders.get(i);
			for (int j = collidedObjects.size() - 1; j >= 0; j--) {
				GameObject collided = collidedObjects.get(j);
				if (collider.getHitbox().intersects(collided.getHitbox().getBounds2D())) {
					collider.getHit(colliders);
					collided.getHit(collidedObjects);
					break;
				}
			}
		}
	}

	public void checkCollision(List<GameObject> colliders, GameObject target) {
		for (int i = colliders.size() - 1; i >= 0; i--) {
			GameObject collider = colliders.get(i);
			if (collider.getHitbox().intersects(target.getHitbox().getBounds2D())) {
				collider.getHit(colliders);
				target.getHit();
			}
		}
	}
}
