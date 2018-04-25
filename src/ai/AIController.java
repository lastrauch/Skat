package ai;

import java.util.ArrayList;
import interfaces.LogicAI;
import logic.Card;
import logic.GameSettings;
import logic.PlayState;
import logic.Player;

public class AIController implements LogicAI{
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
  public void askToPlayCard() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void askToTakeUpSkat() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void updateHand(ArrayList<Card> hand) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setPlayState(PlayState ps) {
    // TODO Auto-generated method stub
    
  }
  
  //TODO Problem: Player and Interface invoke a method askForBet with different return Types
  public void askForBet(int bet){
    
  }

}
