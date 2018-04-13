package interfaces;

import logic.Card;

// Logic to GUI, implemented by GUI
public interface LogicGui {

  /**
   * should show the auction screen 
   */
  public void startAuctionScreen();
  
  /**
   * should start the gui
   */
  public void startGui();
  
  /**
   * should update/ start the user interface of a new round
   */
  public void startNewPlay();
  
  /**
   * asks the player to play a card, returns the index of the chosen card
   * 
   * @param time
   * @return
   */
  public int askToPlayCard(int time);
  
  /**
   * asks the player if he wants to take up the skat
   * 
   * @return
   */
  public boolean takeUpSkat();

  /**
   * supposed to ask the Player if he wants to go with the bet or if he wants to pass like
   * "18 or pass?" (if bet=18) returns false if he wants to pass returns true if he clicks on 18
   * belongs to the methods pass and bet
   * 
   * @param player
   * @param bet
   * @return
   */
  public boolean askForBet(int bet);



}

