package controller;

import java.awt.event.KeyEvent;
import java.util.List;

import entities.Drawable;
import legacy.GameContainer;

public class Controller {

	private GameContainer gameContainer;
    boolean WKeyPressed = false;
    boolean AKeyPressed = false;
    boolean DKeyPressed = false;
    boolean SpaceKeyPressed = false;
	
	public Controller (GameContainer gameContainer) {
		this.gameContainer = gameContainer;
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
	        SpaceKeyPressed = false;
	    }		
	}

	public void keyPress(KeyEvent e) {
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
			System.out.println("pressed");
	    }		
	}
	
	public List<Drawable> getEntities(){
		return gameContainer.getEntities();
	}
	
}
