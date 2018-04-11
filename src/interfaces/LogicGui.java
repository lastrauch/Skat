package interfaces;

import logic.Card;

// Logic to GUI, implemented by GUI
public interface LogicGui {

  /**
   * supposed to open an interface to the player where he can set the play settings (after he won
   * the auction) like PlayMode, trump, hand game ect.
   */
  public void askForPlaySettings();

  /**
   * supposed to open a window to the player where he can set the Game settings (if he is the host
   * of the game) like Count Rule, kontra ect.
   */
  public void askForGameSettings();

  /**
   * supposed to open a window with "pause" and freeses the screen
   */
  public void pauseGame();

  /**
   * 
   * @param time
   * @return
   */
  public Card askToPlayCard(int time);

  /**
   * supposed to ask the Player if he wants to go with the bet or if he wants to pass like
   * "18 or pass?" (if bet=18) returns false if he wants to pass returns true if he clicks on 18
   * 
   * @param player
   * @param bet
   * @return
   */
  public boolean askForBet(int bet);



}

