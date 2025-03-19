package legacy;

import javax.swing.JFrame;

public class AsteroidApp extends JFrame {
	private static final long serialVersionUID = 1L;
	private GameContainer gameContainer = new GameContainer();

	public AsteroidApp() {
		this.add(gameContainer);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700, 700);
		this.setVisible(true);
	}

	public static void main(String args[]) {
		new AsteroidApp(); // obs egentligen SwingUtilities ...
	}
}
