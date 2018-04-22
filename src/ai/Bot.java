package ai;

import database.Cards;
import logic.Card;
import logic.GameSettings;
import logic.PlayState;
import logic.Player;

public class Bot extends Player{
  private BotDifficulty difficulty;
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
 
  public Bot(String name, BotDifficulty difficulty, GameSettings gs){
    super(name);
    this.difficulty = difficulty;
    this.gs = gs;
  }
  
  
}
