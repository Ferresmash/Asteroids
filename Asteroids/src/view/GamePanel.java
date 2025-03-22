package view;

import javax.swing.*;
import entities.Drawable;
import entities.GameObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;




public class GamePanel extends JPanel{
	

	private static final long serialVersionUID = 1L;
	public int screenWidth = getWidth();
    public int screenHeight = getHeight();
    private List<GameObject> gameObjects = new ArrayList<>();
    private int score;
    private int lives;
    private Image heartImage;


    public GamePanel() {
        setPreferredSize(new Dimension(1000, 700));
        setBackground(Color.BLACK);
    }
    
    public void render(List<GameObject> gameObjects) {
    	this.gameObjects = gameObjects;
    	repaint();
        score = 0;
        lives = 5;
        gethearts();
    }

    private void gethearts() {
            heartImage = new ImageIcon(getClass().getResource("/img/heart.png"))
                .getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	}


	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Clears background
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        screenWidth = getWidth();
        screenHeight = getHeight();
        GraphicsRenderVisitor renderer = new GraphicsRenderVisitor(g);

        for (Drawable gameObject : gameObjects) {
        	if(gameObject != null)
        		gameObject.accept(renderer);
		}
        

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.drawString("Score: " + score, 20, 30);
        
        int heartX = screenWidth - (lives * 35);
        int heartY = 10;
        for (int i = 0; i < lives; i++) {
            g2d.drawImage(heartImage, heartX + (i * 35), heartY, this);
        }
        //gameContainer.paintComponent(g);

    }
}
