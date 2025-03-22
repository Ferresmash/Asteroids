package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import java.util.Timer;
import java.util.TimerTask;

import entities.Drawable;
import legacy.GameContainer;
import view.View;

public class Controller implements ActionListener, KeyListener {

	private GameContainer gameContainer;
	private View view;
    boolean WKeyPressed = false;
    boolean AKeyPressed = false;
    boolean DKeyPressed = false;
    boolean SpaceKeyPressed = false;
    private Timer timer = new Timer();
    private boolean isRunning = false;
    private boolean readyToShoot = true;
	
	public Controller (View view, GameContainer gameContainer) {
		this.gameContainer = gameContainer;
		this.view = view;
		start();
		
	}
	
	public void updateContainer() {
		gameContainer.updateContainer(WKeyPressed,
	    AKeyPressed,
	    DKeyPressed,
	    SpaceKeyPressed);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void keyReleased(KeyEvent e) {
	    int keyCode = e.getKeyCode();
	    if (keyCode == KeyEvent.VK_W) {
	        WKeyPressed = false;
	    }
	    if (keyCode == KeyEvent.VK_A) {
	        AKeyPressed = false;
	    }
	    if (keyCode == KeyEvent.VK_D) {
	        DKeyPressed = false;
	    }
	    if (keyCode == KeyEvent.VK_SPACE) { // Use KeyEvent.VK_SPACE for spacebar
	    	readyToShoot = true;
	    }		
	}

	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
	    if (keyCode == KeyEvent.VK_W) {
	        WKeyPressed = true;
	    }
	    if (keyCode == KeyEvent.VK_A) {
	        AKeyPressed = true;
	    }
	    if (keyCode == KeyEvent.VK_D) {
	        DKeyPressed = true;
	    }
	    if (keyCode == KeyEvent.VK_SPACE) { // Use KeyEvent.VK_SPACE for spacebar
	    	if(readyToShoot) {
	    		gameContainer.spawnBullet();
	    		readyToShoot = false;
	    	}
	    		
	    }	
	    if (keyCode == KeyEvent.VK_ESCAPE) {
	    	if(isRunning) {
	    		pause();
	    	} else {
	    		start();
	    	}
	    		
	    }
	}
	
	public List<Drawable> getEntities(){
		return gameContainer.getEntities();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
	
	public void start() {
	    isRunning = true;
	    Thread gameThread = new Thread(() -> {
	        final long FRAME_TIME = 16_000_000; // 16ms in nanoseconds
	        long lastUpdateTime = System.nanoTime();
	        
	        while (isRunning) {
	            long currentTime = System.nanoTime();
	            long elapsed = currentTime - lastUpdateTime;
	            
	            if (elapsed >= FRAME_TIME) {
	                gameloop();
	                lastUpdateTime = currentTime;
	                
	                // Sleep to reduce CPU usage
	                try {
	                    Thread.sleep(1); // Small sleep to prevent CPU hogging
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    });
	    gameThread.start();
	}
	
	public void pause() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        isRunning = false;
    }

    public void stop() {
        pause();
        //model.reset();  // Reset game state if needed
    }
	
	public void gameloop() {
		updateContainer();
		view.render(getEntities());
	}

	
	
}
