package view;

import java.awt.Dimension;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import controller.Controller;
import entities.Drawable;
import legacy.HighScoreManager;

public class View extends JFrame {

	private static final long serialVersionUID = 1L;


	MenuPanel menuPanel;
	GamePanel gamePanel;

	public View(int width, int height) {
		// Use JLayeredPane as the content pane
		JLayeredPane layeredPane = new JLayeredPane();
		setContentPane(layeredPane);
		layeredPane.setPreferredSize(new Dimension(width, height));

		// Initialize and add game panel
		gamePanel = new GamePanel();
		gamePanel.setBounds(0, 0, width, height);
		layeredPane.add(gamePanel, JLayeredPane.DEFAULT_LAYER);

		// Initialize and add menu panel
		menuPanel = new MenuPanel();
		menuPanel.setBounds(0, 0, width, height);
		layeredPane.add(menuPanel, JLayeredPane.PALETTE_LAYER);

		// Configure JFrame
		setTitle("Asteroids");
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	// Other methods remain the same
	public void render(List<Drawable> gameObjects) {
		gamePanel.render(gameObjects);
	}

	public void setController(Controller controller) {
		menuPanel.setController(controller);
	}

	public void switchPanel() {
		menuPanel.setVisible(!menuPanel.isVisible());
	}
	
	public void updateHighScore() {
		menuPanel.updateHighScore();
	}
}
