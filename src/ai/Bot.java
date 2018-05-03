package ai;

import logic.Player;

public class Bot extends Player {
	private static final long serialVersionUID = 1L;
	private BotDifficulty difficulty;

	public Bot(String name, BotDifficulty difficulty) {
		super(name, true);
		this.difficulty = difficulty;
	}

	public void setDifficulty(BotDifficulty difficulty) {
		this.difficulty = difficulty;
	}

	public BotDifficulty getDifficulty() {
		return this.difficulty;
	}

}
