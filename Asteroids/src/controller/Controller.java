package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.SwingUtilities;

import entities.Drawable;
import legacy.Model;
import legacy.GameManager;
import view.View;

public class Controller implements KeyListener {

	private Model model;
	private View view;
	boolean WKeyPressed = false;
	boolean AKeyPressed = false;
	boolean DKeyPressed = false;
	boolean SpaceKeyPressed = false;
	private boolean isRunning = false;
	private boolean readyToShoot = true;
	private long lastUfoSpawnTime = 0;
	private final long ufoSpawnInterval = 10000;
	private long lastUfoShootTime = 0;
	private final long ufoShootInterval = 5000;

	public Controller(View view, Model model) {
		this.model = model;
		this.view = view;
		view.setController(this);
		start();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W) {
			WKeyPressed = false;
		}
		if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A) {
			AKeyPressed = false;
		}
		if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D) {
			DKeyPressed = false;
		}
		if (keyCode == KeyEvent.VK_SPACE) {
			readyToShoot = true;
		}
	}

	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W) {
			WKeyPressed = true;
		}
		if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A) {
			AKeyPressed = true;
		}
		if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D) {
			DKeyPressed = true;
		}
		if (keyCode == KeyEvent.VK_SPACE) {
			if (readyToShoot) {
				model.spawnBullet();
				readyToShoot = false;
			}
		}
		if (keyCode == KeyEvent.VK_ESCAPE) {
			if (isRunning) {
				pause();
			} else {
				start();
			}
		}
	}

	public List<Drawable> getDrawables() {
		return model.getDrawables();
	}

	public void start() {
		isRunning = true;
		Thread gameThread = new Thread(() -> {
			final long FRAME_TIME = 16_000_000;
			long lastUpdateTime = System.nanoTime();
			while (isRunning) {
				long currentTime = System.nanoTime();
				long elapsed = currentTime - lastUpdateTime;
				if (elapsed >= FRAME_TIME) {
					gameloop();
					lastUpdateTime = currentTime;
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		gameThread.start();
	}

	public void pause() {
		isRunning = false;
	}

	public void reset() {
		GameManager.getInstance().reset();
		model.reset();
		view.switchPanel();
	}

	public void gameloop() {
		model.checkCollision();
		model.keepObjectsOnScreen();
		model.removeObjectsOffScreen();
		model.checkForLevelUp();
		model.moveObjects();
		if (SpaceKeyPressed) {
			model.spawnBullet();
		}
		if (WKeyPressed) {
			model.accelerate();
		} else {
			model.stopAcceleration();
		}
		if (AKeyPressed) {
			model.turnLeft();
		}
		if (DKeyPressed) {
			model.turnRight();
		}
		
		long currentTime = System.currentTimeMillis();
		// Spawn UFOs at intervals
		if (currentTime - lastUfoSpawnTime >= ufoSpawnInterval) {
			model.spawnUfo();
			lastUfoSpawnTime = currentTime;
		}
		// Shoot from UFOs at intervals
		if (currentTime - lastUfoShootTime >= ufoShootInterval / (GameManager.getInstance().getLevel() + 1)) {
			model.shootFromUfos();
			lastUfoShootTime = currentTime;
		}

		SwingUtilities.invokeLater(() -> {
			view.render(getDrawables());
			if (GameManager.getInstance().isGameOver()) {
				reset();
				view.updateHighScore();
			}
		});
	}

}
