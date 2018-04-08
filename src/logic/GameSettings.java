package logic;

public class GameSettings {
  private CountRule countRule;
  private int nrOfPlayers;
  private int nrOfPlays;
  private boolean enableKontra;
  private boolean enableLimitedTime;
  private int timeLimit;
  // ... add all other possible settings


  public GameSettings() {
    this.countRule = CountRule.BIERLACHS;
    this.nrOfPlayers = 3;
    this.nrOfPlays = 3;
    this.enableKontra = false;
  }

  public GameSettings(CountRule countRule, int NrOfPlayers, int NrOfPlays) throws LogicException {
    if (this.checkNrOfPlays(NrOfPlays)) {
      this.nrOfPlays = NrOfPlays;
    } else {
      throw new LogicException("The number of plays is not possible!");
    }
    this.countRule = countRule;
    this.nrOfPlayers = NrOfPlayers;
    this.enableKontra = false;
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

  public boolean checkNrOfPlays(int NrOfPlays) {
    int[] possibleNr = {1, 3, 18, 36};

    for (int i = 0; i < possibleNr.length; i++) {
      if (possibleNr[i] == NrOfPlays) {
        return true;
      }
    }
    return false;
  }

  // i think we don't need setters here
  // public void setCountRule(CountRule countRule) {
  // this.countRule = countRule;
  // }
  //
}
