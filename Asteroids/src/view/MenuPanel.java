package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.Controller;
import singleton.HighScoreManager;

public class MenuPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	Controller controller;

	// Define the translucent color
	private final Color translucent = new Color(0, 0, 0, 150);
	private JLabel highScoreLabel;

	public void updateHighScore() {
	    int lastScore = HighScoreManager.getInstance().getLastScore();
	    System.out.println("lastScore at updateHighScore: " + lastScore);
	    highScoreLabel.setText("<html>High Score: " 
	        + HighScoreManager.getInstance().getHighScore()
	        + "<br>Your Score: " + lastScore + "</html>");
	    revalidate();
	    repaint();
	}

	public MenuPanel() {
		setPreferredSize(new Dimension(1000, 700));
		setBackground(translucent);
		setOpaque(false);

		setLayout(new GridLayout(3, 3));

		JLabel titleLabel = new JLabel("ASTEROIDS", SwingConstants.CENTER);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
		titleLabel.setForeground(Color.WHITE);
		JPanel titlePanel = emptyPanel();
		titlePanel.setLayout(new GridLayout(1, 1));
		titlePanel.add(titleLabel);

		JButton startButton = new JButton("Start Game");
		startButton.setBackground(translucent);
		startButton.setFont(new Font("Arial", Font.BOLD, 18));
		startButton.setForeground(Color.WHITE);
		startButton.setBorder(null);
		startButton.addActionListener(e -> {
			if (controller != null) {
				controller.reset();
			}
		});

		JPanel highscorePanel = emptyPanel();
		highScoreLabel = new JLabel("High Score: " + HighScoreManager.getInstance().getHighScore()
				+ " \r\n Your Score: " + HighScoreManager.getInstance().getLastScore(), SwingConstants.CENTER);
		highScoreLabel.setFont(new Font("Arial", Font.BOLD, 18));
		highScoreLabel.setForeground(Color.WHITE);
		highscorePanel.add(highScoreLabel);

		add(emptyPanel());
		add(titlePanel);
		add(emptyPanel());
		add(emptyPanel());
		add(startButton);
		add(emptyPanel());
		add(emptyPanel());
		add(highscorePanel);
		add(emptyPanel());
		setVisible(true);
	}

	private JPanel emptyPanel() {
		JPanel panel = new JPanel();
		panel.setBackground(translucent);
		return panel;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
}
