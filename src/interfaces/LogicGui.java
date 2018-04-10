package interfaces;

import logic.Card;
import logic.PlayMode;
import logic.PlayState;
import logic.Player;

//Logic to GUI, implemented by GUI
public interface LogicGui {
 
  /**
   * gives the logic the Card the player klicks on in the user interface.
   * 
   * @param player
   * @return
   */
  public Card getChosenCard(Player player);
  
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
  public boolean askForBet(Player player, int bet);
  
  /**
   * supposed to ask the Player if the wants to take up the stack
   * returns true if the player wants to play hand (take up the stack)
   * @param player
   * @return
   */
  public boolean askForHandGame(Player player);
  
  /**
   * asks player to set the PlayMode (after he won the auction)
   * supposed to return
   * PlayMode.COLOUR || PlayMode.GRAND || PlayMode.NULL || PlayMode.NULLOUVERT
   * 
   * @param player
   * @return
   */
  public PlayMode setPlayMode(Player player);
  
  /**
   * asks player to set the Trump (after he won the auction AND set the PlayMode = colour)
   * supposed to return
   * Colour.CLUBS || Colour.SPADES || Colour.HEARTS || Colour.DIAMONDS
   * 
   * @param player
   * @return
   */
  public String setTrump(Player player);
  
  //add all other play settings and the conditions (when the could be set)
}
