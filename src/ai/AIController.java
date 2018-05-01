package ai;

import java.util.ArrayList;
import java.util.List;
import interfaces.InGameInterface;
import logic.Card;
import logic.ClientLogic;
import logic.GameSettings;
import logic.PlayState;
import logic.Player;
import logic.Position;

public class AIController implements InGameInterface{
  private Bot bot;
  private GameSettings gs;
  private PlayState ps;
  private List<Player> opponents;
  private Player partner;
  private int[] bets;   //Vector of bets by player i
  private int maxBet = 0; //Set to -1, if bot doesn't want to play single
  private SinglePlay singlePlay;
  private Card[][] playedCards; //Matrix of played Cards. Columns are the players, rows are the Cards
  private double[][] cardProbability; //Matrix of probabilities, player i has card j;
                                      //Player are the columns, probabilities are the rows
                                      //Spades, Clubs, Hearts, Diamonds
                                      //Ace, Ten, King, Queen, Jack, Nine, Eight, Seven
  private boolean[][] hasColour;  //Columns are the players, rows are the colours: Spades, Clubs, Hearts, Diamonds
  private boolean[] hasTrump;   //Index is the player
  private int existingTrumps;   //Trumps left in the whole game, including own cards
  private List<Card> currentTrick;
  
  public AIController(String name, BotDifficulty difficulty, GameSettings gs){
    this.bot = new Bot(name, difficulty);
    this.gs = gs;
    this.opponents = new ArrayList<Player>();
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

  
  public PlayState askToTakeUpSkat(PlayState ps) {
	  //TODO SinglePlayer und Multiplayer updaten
    this.ps = ps;
	  switch (this.bot.getDifficulty()){
      case EASY: return Easy.setPlayState(this);
      case MEDIUM: return Medium.setPlayState(this);
      case HARD: return Hard.setPlayState(this);
    }  
	return null;  
  }

  
  public boolean askForBet(int bet, Player player) {
    //TODO Player is the one who put the last bet; Null if one is the first one to bet
	switch (this.bot.getDifficulty()){
      case EASY: return Easy.setBet(this, bet);
      case MEDIUM: return Medium.setBet(this, bet);
      case HARD: return Hard.setBet(this, bet);
      default: return false;
    }
  }
  
  
  public void updateHand(List<Card> hand) {
    if(this.bot.getHand().size() == 0){
      this.cardProbability = General.initializeProbabilities(hand);
    }
    this.bot.setHand(hand);
  }

  
  public void setPlaySettings(PlayState ps) {
	  //TODO SinglePlayer and MultiPlayer updaten
	  //TODO check if I want to play kontra
    switch(ps.getPlayMode()){
      case GRAND: this.existingTrumps = 4; break;
      case SUIT: this.existingTrumps = 11; break;
      case NULL: this.existingTrumps = 0; break;
    }
    this.ps = ps;
  }

  
  public void updateTrick(List<Card> currentTrick) {
    this.currentTrick = currentTrick;
  }

  
  public void setGameSettings(GameSettings gs) {
    this.gs = gs;
  }
  

	public void stopGame(String reason) {
		//Do nothing
	}
	
	public void showWinnerTrick(Player player) {
		//Do nothing
	}
	
	public void showWinnerPlay(Player player1, Player player2) {
		//Do nothing
	}
	
	public void showWinnerGame(Player player) {
		//Do nothing
	}
	
	@Override
	public void startPlay(List<Card> hand, Position position) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	  public boolean askToRekontra() {
	    // TODO Auto-generated method stub
	    return false;
	  }
  
  public GameSettings getGameSettings(){
    return this.gs;
  }
  
  public Bot getBot(){
	  return this.bot;
  }
  
  public PlayState getPlayState(){
	  return this.getPlayState();
  }
  
  public List<Card> getCurrentTrick(){
	  return this.currentTrick;
  }
  
  public void setCardProbabilities(double[][] cardProbabilities){
	  this.cardProbability = cardProbabilities;
  }
  
  public double[][] getCardProbabilities(){
    return this.cardProbability;
  }
  
  public void setCardProbability(double probability, int colour, int number, int player){
	  this.cardProbability[colour*8+number][player] = probability;
  }
  
  public void setMaxBet(int maxBet){
	  this.maxBet = maxBet;
  }
  
  public int getMaxBet(){
	  return this.maxBet;
  }
  
  public void setSinglePlay(SinglePlay singlePlay){
	  this.singlePlay = singlePlay;
  }
  
  public SinglePlay getSinglePlay(){
	  return this.singlePlay;
  }
  
  

}
