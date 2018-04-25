package ai;

import java.util.ArrayList;
import java.util.List;
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
  private List<Card> currentTrick;
  
  public AIController(String name, BotDifficulty difficulty, GameSettings gs){
    this.bot = new Bot(name, difficulty);
    this.gs = gs;
  }

  public void startPlay(ArrayList<Card> hand, Position position) {
    this.bot.setHand(hand);
    this.bot.setPosition(position);
  }
  
  public int askToPlayCard() {
    switch (this.bot.getDifficulty()){
      case EASY: return Easy.playCard(this);
      case MEDIUM: return Medium.playCard(this);
      case HARD: return Hard.playCard(this);
      default: return -1;
    }
  }

  
  public void showSecoundsLeftToPlayCard(int seconds) {
    //Do nothing and expect the AI to react in sufficient time.
  }

  
  public void askToTakeUpSkat(PlayState ps) {
    // TODO Auto-generated method stub
    
  }

  
  public boolean askForBet(int bet) {
    switch (this.bot.getDifficulty()){
      case EASY: return Easy.setBet(this, bet);
      case MEDIUM: return Medium.setBet(this, bet);
      case HARD: return Hard.setBet(this, bet);
      default: return false;
    }
  }

  
  public void updateHand(ArrayList<Card> hand) {
   this.bot.setHand(hand);
  }

  
  public void setPlaySettings(PlayState ps) {
    this.ps = ps;
  }

  
  public void updateTrick(ArrayList<Card> currentTrick) {
    this.currentTrick = currentTrick;
  }

  
  public void setGameSettings(GameSettings gs) {
    this.gs = gs;
  }
  
  public GameSettings getGameSettings(){
    return this.gs;
  }
  
  public Bot getBot(){
	  return this.bot;
  }
}
