package legacy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class HighScoreManager {

	private static HighScoreManager instance;
	private int highScore;
	private final String HIGHSCORE_PATH = "../resources/highscores.txt";

	private HighScoreManager() {
		loadHighScore();
	}

	public static HighScoreManager getInstance() {
		if (instance == null) {
			instance = new HighScoreManager();
		}
		return instance;
	}

	public void addHighScore(int newScore) {
		System.out.println("newScore: " + newScore);
		System.out.println("highScore: " + highScore);
		if (newScore > highScore) {
			System.out.println("New score added: " + newScore);
			this.highScore = newScore;
			saveHighScore();
		}
	}

	public int getHighScore() {
		return highScore;
	}

	private void loadHighScore() {
		File file = new File(HIGHSCORE_PATH);
		if (!file.exists()) {
			return;
		}
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line = br.readLine();
			if (line != null) {
				try {
					highScore = Integer.parseInt(line.trim());
				} catch (NumberFormatException e) {
					System.err.println("Invalid HighScore format in file.");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void saveHighScore() {
		File file = new File(HIGHSCORE_PATH);
		File parentDir = file.getParentFile();
		if (parentDir != null && !parentDir.exists()) {
			parentDir.mkdirs();
		}
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
			bw.write(Integer.toString(highScore));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
