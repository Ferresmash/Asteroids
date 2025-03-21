package view;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JFrame;

import entities.Drawable;

public class View extends JFrame{
	
	GamePanel gamePanel;
	MenuPanel menuPanel;
	
	public View(GamePanel gamePanel, MenuPanel menuPanel) {
		this.gamePanel = gamePanel;
		this.menuPanel = menuPanel;
		gamePanel.setVisible(true);
		menuPanel.setVisible(false);
		
	}
	
	public void render(List<Drawable> gameObject) {
		gamePanel.render(gameObject);
	}
	
	public void pauseGame() {
		gamePanel.setVisible(false);
		menuPanel.setVisible(true);
	}
	
	public void playGame() {
		gamePanel.setVisible(true);
		menuPanel.setVisible(false);
	}
	
}
