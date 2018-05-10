package logic;

import java.io.Serializable;

/**
 * in the GameSettings we can save all the settings, the controler sets, which are send to every
 * client in the same lobby.
 *
 */
public class GameSettings implements Serializable {

  private static final long serialVersionUID = 1L;
  private CountRule countRule;
  private int nrOfPlayers;
  private int nrOfPlays;
  private boolean enableKontra;
  private boolean enableLimitedTime;
  private int timeLimit;
  private int endPointsBierlachs;
  private GameMode gameMode;
  private int randomSeatingIndex;
  // ... add all other possible settings

  /**
   * constructor.
   */
  public GameSettings() {
    this.countRule = CountRule.NORMAL;
    this.nrOfPlayers = 3;
    this.nrOfPlays = 3;
    this.enableKontra = false;
    this.enableLimitedTime = false;
    this.timeLimit = 60;
    this.endPointsBierlachs = 500;
    this.randomSeatingIndex = (int) (Math.random() * this.nrOfPlayers + 1);
  }

  /**
   * constructor.
   * 
   * @param countRule of game
   * @param nrOfPlayers in game
   * @param nrOfPlays of game
   * @throws LogicException if not possible
   */
  public GameSettings(CountRule countRule, int nrOfPlayers, int nrOfPlays) throws LogicException {
    // if the count rule is bierlachs we need an end score where the game determines
    this.countRule = countRule;
    if (countRule == CountRule.BIERLACHS) {
      this.endPointsBierlachs = 500;
      this.nrOfPlays = 50; // we want the game to stop even there is no winner after 50 plays

    } else {
      if (this.checkNrOfPlays(nrOfPlays)) {
        this.nrOfPlays = nrOfPlays;
      } else {
        throw new LogicException("The number of plays is not possible!");
      }
    }
    this.nrOfPlayers = nrOfPlayers;
    this.enableKontra = false;
    this.randomSeatingIndex = (int) (Math.random() * this.nrOfPlayers + 1);
  }

  /**
   * returns true, if the settet number of plays is played.
   * 
   * @param nrOfPlays which are played already
   * @return true if settet is played
   */
  public boolean checkNrOfPlays(int nrOfPlays) {
    int[] possibleNr = {1, 3, 18, 36};

    for (int i = 0; i < possibleNr.length; i++) {
      if (possibleNr[i] == nrOfPlays) {
        return true;
      }
    }
    return false;
  }

  public CountRule getCountRule() {
    return this.countRule;
  }

  public void setCountRule(CountRule countRule) {
    this.countRule = countRule;
  }

  public int getNrOfPlayers() {
    return this.nrOfPlayers;
  }

  public int getNrOfPlays() {
    return this.nrOfPlays;
  }

  public int getEndPointsBierlachs() {
    return this.endPointsBierlachs;
  }

  public void setEndPointsBierlachs(int endPoints) {
    this.endPointsBierlachs = endPoints;
  }

  public void setEnableKontra(boolean ek) {
    this.enableKontra = ek;
  }

  public boolean isEnableKontra() {
    return this.enableKontra;
  }

  public void setLimitedTime(boolean lt) {
    this.enableLimitedTime = lt;
  }

  public boolean isLimitedTime() {
    return this.enableLimitedTime;
  }

  public void setTimeLimit(int sec) {
    this.timeLimit = sec;
  }

  public int getTimeLimit() {
    return this.timeLimit;
  }

  public void setNrOfPlayers(int nr) {
    this.nrOfPlayers = nr;
  }

  public GameMode getGameMode() {
    return gameMode;
  }

  public void setGameMode(GameMode gameMode) {
    this.gameMode = gameMode;
  }

  public int getRandomSeatingIndex() {
    return this.randomSeatingIndex;
  }

  public void setRandomSeatingIndex(int seatingOrder) {
    this.randomSeatingIndex = seatingOrder;
  }

  public void setNumberOfPlays(int numberOfPlays) {
    this.nrOfPlays = numberOfPlays;
  }
}
