package app;

import javax.swing.JFrame;

import controller.Controller;
import legacy.GameContainer;
import view.GamePanel;
import view.MenuPanel;
import view.View;

public class MainFrame extends JFrame {
	
	public MainFrame() {
		GameContainer gameContainer = new GameContainer(1000, 700);
		
		setTitle("Asteroids");
		
		GamePanel gamePanel = new GamePanel();
		MenuPanel menuPanel = new MenuPanel();
		View view = new View(gamePanel, menuPanel);
		add(gamePanel);
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		Controller controller = new Controller(view, gameContainer);
		addKeyListener(controller);
		setFocusable(true);
		requestFocusInWindow();
	}

}
