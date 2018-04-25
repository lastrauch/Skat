package ai;

import java.util.ArrayList;
import interfaces.InGameInterface;
import logic.Card;
import logic.GameSettings;
import logic.PlayState;
import logic.Player;
import logic.Position;

public class AIController implements InGameInterface{
  private Bot bot;
  private GameSettings gs;
  private PlayState ps;
  private Player[] opponents;
  private Player partner;
  private int[] bets;   //Vector of bets by player i
  private Card[][] playedCards; //Matrix of played Cards. Columns are the plyers, Rows are the Cards
  private double[][] cardProbability; //Matrix of probabilities, payer i has card j; Player are the columns, probabilities are the rows
  private boolean[] hasColour;
  private boolean[] hasTrump;
  private int existingTrumps;   //Trumps left in the whole game, including own cards
 
  public AIController(String name, BotDifficulty difficulty, GameSettings gs){
    this.bot = new Bot(name, difficulty);
    this.gs = gs;
  }

  @Override
  public void startPlay(ArrayList<Card> hand, Position position) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public int askToPlayCard() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public void showSecoundsLeftToPlayCard(int seconds) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void askToTakeUpSkat(PlayState ps) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public boolean askForBet(int bet) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void updateHand(ArrayList<Card> hand) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setPlaySettings(PlayState ps) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void updateTrick(ArrayList<Card> currentTrick) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setGameSettings(GameSettings gs) {
    // TODO Auto-generated method stub
    
  }

}
