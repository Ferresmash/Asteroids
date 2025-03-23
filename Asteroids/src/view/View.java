package view;

import java.awt.Dimension;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import controller.Controller;
import entities.Drawable;

public class View extends JFrame {

	private static final long serialVersionUID = 1L;


	MenuPanel menuPanel;
	GamePanel gamePanel;

	public View(int width, int height) {
		JLayeredPane layeredPane = new JLayeredPane();
		setContentPane(layeredPane);
		layeredPane.setPreferredSize(new Dimension(width, height));

		gamePanel = new GamePanel();
		gamePanel.setBounds(0, 0, width, height);
		layeredPane.add(gamePanel, JLayeredPane.DEFAULT_LAYER);

		menuPanel = new MenuPanel();
		menuPanel.setBounds(0, 0, width, height);
		layeredPane.add(menuPanel, JLayeredPane.PALETTE_LAYER);

		setTitle("Asteroids");
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

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
