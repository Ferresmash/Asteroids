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

public class MenuPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    
    Controller controller;
    
    // Define the translucent color
    private final Color translucent = new Color(0, 0, 0, 150);

    public MenuPanel() {
        // Set the overall panel properties
        setPreferredSize(new Dimension(1000, 700));
        setBackground(translucent);
        setOpaque(false);
        
        // Use a 3x3 grid layout with gaps between cells
        setLayout(new GridLayout(3, 3)); 

        // Create the title label
        
        
     // Create the title label
        JLabel titleLabel = new JLabel("ASTEROIDS", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE); // Optional: improves visibility
        JPanel titlePanel = emptyPanel();
        titlePanel.setLayout(new GridLayout(1, 1)); // Ensure label fills panel
        titlePanel.add(titleLabel);

        // Create the high score label
        JLabel highScoreLabel = new JLabel("High Score: 0", SwingConstants.CENTER);
        highScoreLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        highScoreLabel.setForeground(Color.WHITE); // Optional: improves visibility
        JPanel highscorePanel = emptyPanel();
        highscorePanel.setLayout(new GridLayout(1, 1)); // Ensure label fills panel
        highscorePanel.add(highScoreLabel);

        // Create the start button
        JButton startButton = new JButton("Start Game");
        startButton.setBackground(translucent);
        startButton.setFont(new Font("Arial", Font.BOLD, 18));
        startButton.setForeground(Color.WHITE); // Optional: improves visibility
        startButton.setBorder(null);
        startButton.addActionListener(e -> {
            if (controller != null) {
                controller.reset();
            }
        });


        // Add components to the grid layout
        // First row: empty, title, empty
        add(emptyPanel());         // Cell (0,0): empty
        add(titlePanel);           // Cell (0,1): Game title
        add(emptyPanel());         // Cell (0,2): empty

        // Second row: empty, start button, high score
        add(emptyPanel());         // Cell (1,0): empty
        add(startButton);          // Cell (1,1): Start button
        add(highscorePanel);       // Cell (1,2): High score label

        // Third row: empty, empty, empty
        add(emptyPanel());         // Cell (2,0): empty
        add(emptyPanel());         // Cell (2,1): empty
        add(emptyPanel());         // Cell (2,2): empty

        setVisible(true);
    }
    
    // Create an "empty" panel with the translucent background
    private JPanel emptyPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(translucent);
        return panel;
    }
    
    
    public void setController(Controller controller) {
        this.controller = controller;
    }
}
