package logic;

public class GameSettings {
  private CountRule countRule;
  private int NrOfPlayers;
  private int NrOfPlays;
  // ... add all other possible settings
  
  public GameSettings(CountRule countRule, int NrOfPlayers, int NrOfPlays) {
    this.countRule = countRule;
    this.NrOfPlayers = NrOfPlayers;
    this.NrOfPlays = NrOfPlays;
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
  
  //i think we don't need setters here
//  public void setCountRule(CountRule countRule) {
//    this.countRule = countRule;
//  }
//  
}