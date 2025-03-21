package app;

import javax.swing.JFrame;

import controller.Controller;
import legacy.GameContainer;
import view.GamePanel;

public class AsteroidApp extends JFrame {
	private static final long serialVersionUID = 1L;
	// private GameContainer gameContainer = new GameContainer();

	public static void main(String[] args) {
		GameContainer gameContainer = new GameContainer(1000, 700);
		
		JFrame frame = new JFrame("Asteroids");
		GamePanel gamePanel = new GamePanel(controller);
		frame.add(gamePanel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		Controller controller = new Controller(frame, gameContainer);
	}
}
