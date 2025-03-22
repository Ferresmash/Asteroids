package legacy;

public class GameManager {
	
	private static GameManager instance;
	private int score;
	private int lives;
	private int level;
	
	
	private GameManager() {
		reset();
	}
	
    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public void increaseScore(int amount) {
        score += amount;
    }
    public void increaseLevel() {
        level += 1;
    	System.out.println("Increased level, Now Level: " + level);
    }
    public void decreaseLives() {
        lives -= 1;
    }

    public int getScore() {
        return score;
    }
    public int getLives() {
    	return lives;
    }
    public int getLevel() {
    	return level;
    }

    public void reset() {
        score = 0;
    	lives = 5;
    	level = 0;
    }

}
