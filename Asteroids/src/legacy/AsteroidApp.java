package legacy;

import javax.swing.JFrame;

public class AsteroidApp extends JFrame {
	private static final long serialVersionUID = 1L;
	private GameContainer gameContainer = new GameContainer();


	public static void main(String args[]) {
		
		Game game = new Game();
	    game.start();
	}
}
