package interfaces;

import logic.Card;

//Logic to GUI, implemented by GUI
public interface LogicGui {
 
  /**
   * 
   */
  public void askForPlaySettings();
  
  /**
   * 
   */
  public void askForGameSettings();
  
  /**
   * 
   */
  public void pauseGame();
  
  /**
   * 
   * @param time
   * @return
   */
  public Card askToPlayCard(int time);
  
  /**
   * supposed to ask the Player if he wants to go with the bet or if he wants to pass
   * like "18 or pass?" (if bet=18) 
   * returns false if he wants to pass
   * returns true if he clicks on 18
   * 
   * @param player
   * @param bet
   * @return
   */
  public boolean askForBet(int bet);
  
  
  
}

