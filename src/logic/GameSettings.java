package logic;

public class GameSettings {
  private CountRule countRule;
  private int NrOfPlayers;
  private int NrOfPlays;
  private boolean inableKontra;
  // ... add all other possible settings

  
  public GameSettings() {
    this.countRule = CountRule.NORMAL;
    this.NrOfPlayers = 3;
    this.NrOfPlays = 3;
    this.inableKontra = false; 
  }
  
  public GameSettings(CountRule countRule, int NrOfPlayers, int NrOfPlays) throws LogicException {
    if (this.checkNrOfPlays(NrOfPlays)) {  
      this.NrOfPlays = NrOfPlays;
    }else {
      throw new LogicException("The number of plays is not possible!");
    }
    this.countRule = countRule;
    this.NrOfPlayers = NrOfPlayers;
    this.inableKontra = false;
  }

  public CountRule getCountRule() {
    return this.countRule;
  }

  public int getNrOfPlayers() {
    return this.NrOfPlayers;
  }

  public int getNrOfPlays() {
    return this.NrOfPlays;
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
