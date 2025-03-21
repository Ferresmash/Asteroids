package view;

import javax.swing.*;

import controller.Controller;
import entities.Drawable;
import legacy.GameContainer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GamePanel extends JPanel{
	

	private static final long serialVersionUID = 1L;
	public int screenWidth = getWidth();
    public int screenHeight = getHeight();

    public GamePanel() {
        setPreferredSize(new Dimension(1000, 700));
        setBackground(Color.BLACK);
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Clears background
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        screenWidth = getWidth();
        screenHeight = getHeight();
        controller.setScreenSize(screenWidth, screenHeight);
        GraphicsRenderVisitor renderer = new GraphicsRenderVisitor(g);

        for (Drawable entity : controller.getEntities()) {
			entity.accept(renderer);
		}
        
        // Draw example moving object
        g2d.setColor(Color.WHITE);

        //gameContainer.paintComponent(g);

    }
}
