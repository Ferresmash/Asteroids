package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import entities.Drawable;

public class View extends JFrame {
    
    GamePanel gamePanel;
    MenuPanel menuPanel;
    JPanel containerPanel;
    
    public View(GamePanel gamePanel, MenuPanel menuPanel) {
        this.gamePanel = gamePanel;
        this.menuPanel = menuPanel;
        

    }
    
    public void render(List<Drawable> gameObject) {
        gamePanel.render(gameObject);
    }
    
    public void pauseGame() {

    }
    
    public void playGame() {

    }
}
