package view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {
	
	public MenuPanel() {
		JButton startButton = new JButton("Start");
		startButton.setBackground(Color.green);
		add(startButton);
		
	}

}
