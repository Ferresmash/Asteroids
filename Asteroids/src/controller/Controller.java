package controller;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import entities.Drawable;
import legacy.GameContainer;
import legacy.GameManager;
import view.View;

public class Controller implements ActionListener, KeyListener {

	private GameContainer gameContainer;
	private View view;
    boolean WKeyPressed = false;
    boolean AKeyPressed = false;
    boolean DKeyPressed = false;
    boolean SpaceKeyPressed = false;
    private boolean isRunning = false;
    private boolean readyToShoot = true;
	
	public Controller (View view, GameContainer gameContainer) {
		this.gameContainer = gameContainer;
		this.view = view;
		view.setController(this);
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
	    if (keyCode == KeyEvent.VK_UP) {
	        WKeyPressed = false;
	    }
	    if (keyCode == KeyEvent.VK_LEFT) {
	        AKeyPressed = false;
	    }
	    if (keyCode == KeyEvent.VK_RIGHT) {
	        DKeyPressed = false;
	    }
	    if (keyCode == KeyEvent.VK_SPACE) { // Use KeyEvent.VK_SPACE for spacebar
	    	readyToShoot = true;
	    }		
	}

	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
	    if (keyCode == KeyEvent.VK_UP) {
	        WKeyPressed = true;
	    }
	    if (keyCode == KeyEvent.VK_LEFT) {
	        AKeyPressed = true;
	    }
	    if (keyCode == KeyEvent.VK_RIGHT) {
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
        isRunning = false;
    }

	public void reset() {
		GameManager.getInstance().reset();
		gameContainer.reset();
		view.switchPanel();
	}
	
    public void stop() {
        pause();
        //model.reset();  // Reset game state if needed
    }
	
	public void gameloop() {
		updateContainer();
		//gameContainer.shootFromUfos();
		view.render(getEntities());
		if(GameManager.getInstance().isGameOver()) {
			reset();			
		}
	}

	
	
}
