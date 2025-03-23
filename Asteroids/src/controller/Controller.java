package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.SwingUtilities;
import command.AccelerateCommand;
import command.Command;
import command.FireCommand;
import command.PauseCommand;
import command.StopAccelerateCommand;
import command.TurnLeftCommand;
import command.TurnRightCommand;
import gameObjects.Drawable;
import model.Model;
import singleton.GameManager;
import view.View;

public class Controller implements KeyListener {

	private Model model;
	private View view;
	private boolean isRunning = false;

	private long lastUfoSpawnTime = 0;
	private final long ufoSpawnInterval = 10000;
	private long lastUfoShootTime = 0;
	private final long ufoShootInterval = 5000;

	private final Map<Integer, Command> pressCommands = new HashMap<>();
	private final Map<Integer, Command> holdCommands = new HashMap<>();
	private final Map<Integer, Command> releaseCommands = new HashMap<>();
	private final Map<Integer, Command> activeCommandsMap = new HashMap<>();

	public Controller(View view, Model model) {
		this.model = model;
		this.view = view;
		view.setController(this);

		Command accelerate = new AccelerateCommand(model);
		Command turnLeft = new TurnLeftCommand(model);
		Command turnRight = new TurnRightCommand(model);
		Command fire = new FireCommand(model);
		Command pause = new PauseCommand(this);

		holdCommands.put(KeyEvent.VK_W, accelerate);
		holdCommands.put(KeyEvent.VK_UP, accelerate);
		holdCommands.put(KeyEvent.VK_A, turnLeft);
		holdCommands.put(KeyEvent.VK_LEFT, turnLeft);
		holdCommands.put(KeyEvent.VK_D, turnRight);
		holdCommands.put(KeyEvent.VK_RIGHT, turnRight);
		pressCommands.put(KeyEvent.VK_SPACE, fire);
		holdCommands.put(KeyEvent.VK_ESCAPE, pause);
		releaseCommands.put(KeyEvent.VK_W, new StopAccelerateCommand(model));
		releaseCommands.put(KeyEvent.VK_UP, new StopAccelerateCommand(model));

		start();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Command command;

		if (pressCommands.containsKey(e.getKeyCode())) {
			command = pressCommands.get(e.getKeyCode());
			if (command != null) {
				command.execute();
			}

		} else if (holdCommands.containsKey(e.getKeyCode())) {
			command = holdCommands.get(e.getKeyCode());
			if (command != null) {
				activeCommandsMap.put(e.getKeyCode(), command);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		activeCommandsMap.remove(e.getKeyCode());
		if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
			new StopAccelerateCommand(model).execute();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
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

		for (Command c : activeCommandsMap.values()) {
			// This will cause accelerate, turnLeft, turnRight, etc. to happen every frame
			c.execute();
		}

		model.checkCollision();
		model.keepObjectsOnScreen();
		model.removeObjectsOffScreen();
		model.checkForLevelUp();
		model.moveObjects();

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

	public boolean isRunning() {
		return isRunning;
	}

}
