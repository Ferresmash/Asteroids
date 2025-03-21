package app;

import javax.swing.WindowConstants;

import controller.Controller;
import legacy.GameContainer;
import view.View;

public class AsteroidApp {

	public static void main(String[] args) {

		GameContainer gameContainer = new GameContainer(1000, 700);
		View view = new View();
		Controller controller = new Controller(view, gameContainer);
		view.addKeyListener(controller);
		view.setFocusable(true);
		view.requestFocusInWindow();
		view.setSize(1000, 700);
		view.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		view.requestFocusInWindow();
	}
}
