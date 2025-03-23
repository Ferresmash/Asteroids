package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.SwingUtilities;

import gameObjects.Drawable;
import model.GameContainer;
import singleton.GameManager;
import view.View;

public class Controller implements KeyListener {

	private GameContainer gameContainer;
	private View view;
	boolean WKeyPressed = false;
	boolean AKeyPressed = false;
	boolean DKeyPressed = false;
	boolean SpaceKeyPressed = false;
	private boolean isRunning = false;
	private boolean readyToShoot = true;

	public Controller(View view, GameContainer gameContainer) {
		this.gameContainer = gameContainer;
		this.view = view;
		view.setController(this);
		start();
	}

	public void updateContainer() {
		gameContainer.updateContainer(WKeyPressed, AKeyPressed, DKeyPressed, SpaceKeyPressed);
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
				gameContainer.spawnBullet();
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

	public List<Drawable> getEntities() {
		return gameContainer.getEntities();
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
		gameContainer.reset();
		view.switchPanel();
	}

	public void gameloop() {
		updateContainer();
		SwingUtilities.invokeLater(() -> {
			view.render(getEntities());
			if (GameManager.getInstance().isGameOver()) {
				reset();
				view.updateHighScore();
			}
		});
	}

}
