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
}