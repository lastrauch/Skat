package ai;

import logic.Card;
import logic.GameSettings;
import logic.PlayState;
import logic.Player;

public class Bot extends Player{
  private BotDifficulty difficulty;
 
  public Bot(String name, BotDifficulty difficulty){
    super(name, true);
    this.difficulty = difficulty;
  }
  
  public void setDifficulty(BotDifficulty difficulty){
    this.difficulty = difficulty;
  }
  
  public BotDifficulty getDifficulty(){
    return this.difficulty;
  }
  
}
