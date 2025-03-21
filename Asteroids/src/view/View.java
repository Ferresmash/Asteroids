package view;

import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

import entities.Drawable;

public class View extends JFrame {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GamePanel gamePanel;
    MenuPanel menuPanel;
    JPanel containerPanel;
    
    public View() {
        this.gamePanel = new GamePanel();
        this.menuPanel = new MenuPanel();
		setTitle("Asteroids");
        gamePanel.setVisible(true);
        add(gamePanel);
        pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
    }
    
    public void render(List<Drawable> gameObject) {
        gamePanel.render(gameObject);
    }
    
    public void pauseGame() {

    }
    
    public void playGame() {

    }
}
