package app;

import javax.swing.WindowConstants;

import controller.Controller;
import legacy.GameContainer;
import view.View;

public class AsteroidApp {
	
	private static final int WIDTH = 1000;
	private static final int HEIGHT = 700;

	public static void main(String[] args) {
		
		GameContainer gameContainer = new GameContainer(WIDTH, HEIGHT);
		View view = new View(WIDTH, HEIGHT);
		Controller controller = new Controller(view, gameContainer);
		view.addKeyListener(controller);
		view.setFocusable(true);
		view.requestFocusInWindow();
		view.setSize(WIDTH+16, HEIGHT+39);
		view.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		view.requestFocusInWindow();
		view.setResizable(false);
	}
}
