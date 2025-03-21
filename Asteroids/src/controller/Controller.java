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
	
	public void setScreenSize(int screenWidth, int screenHeight) {
		gameContainer.setScreenSize(screenWidth, screenHeight);
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
	}
	
	public List<Drawable> getEntities(){
		return gameContainer.getEntities();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/* Game Loop*/
		
		// Update model
		
		// Rerender view
	}
	
	public void start() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                gameloop();
            }
        }, 0, 16);  // Runs every 16 ms
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
		setScreenSize(1000,700);
		updateContainer();
		view.render(getEntities());
	}

	
	
}
