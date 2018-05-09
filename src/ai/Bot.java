package ai;

import logic.Player;

public class Bot extends Player {

  // This is a class that represents a bot. It has the same and more attributes
  // and functions like Player, which it extends.

  private static final long serialVersionUID = 1L;
  private BotDifficulty difficulty;

  //////////////////////////////////////////////////////////////////////////////////////////////////
  // Constructor
  //////////////////////////////////////////////////////////////////////////////////////////////////
  /**
   * Constucts a bot with given difficulty.
   * 
   * @author fkleinoe
   * @param name of the AI
   * @param difficulty of the AI
   */
  public Bot(String name, BotDifficulty difficulty) {
    super(name, true);
    this.difficulty = difficulty;
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////
  // Setter- and Getter-Methods
  //////////////////////////////////////////////////////////////////////////////////////////////////
  /**
   * Set difficulty.
   * 
   * @author fkleinoe
   * @param difficulty to set
   */
  public void setDifficulty(BotDifficulty difficulty) {
    this.difficulty = difficulty;
  }

  /**
   * Get difficutly.
   * 
   * @author fkleinoe
   * @return BotDifficulty to get
   */
  public BotDifficulty getDifficulty() {
    return this.difficulty;
  }

}
