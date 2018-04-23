package interfaces;

import java.util.ArrayList;
import logic.Card;
import logic.PlayState;
import logic.Position;
import logic.Trick;

public interface InGameInterface {

  /**
   * should open the InGameScreen and show the Player his cards and his position
   * 
   * @param hand
   * @param position
   */
  public void startPlay(ArrayList<Card> hand, Position position);

  
  /**
   * asks the player to play a card
   * 
   */
  public int askToPlayCard();

  /**
   * should show the number "seconds" at the corner of the screen
   * 
   * @param seconds
   */
  public void showSecoundsLeftToPlayCard(int seconds);

  /**
   * asks the player if he wants to take up the skat
   * 
   */
  public void askToTakeUpSkat(PlayState ps);

  /**
   * supposed to ask the Player if he wants to go with the bet or if he wants to pass like
   * "18 or pass?" (if bet=18) 
   * 
   * @param bet
   */
  public boolean askForBet(int bet);

  /**
   * should reload the hand cards in the given order
   * 
   * @param hand
   */
  public void updateHand(ArrayList<Card> hand);

  /**
   * should open the play settings screen and edit the playState ps with setters
   * 
   * @param ps
   */
  public void setPlaySettings(PlayState ps);

  /**
   * updates the current trick
   * @param currentTrick
   */
  public void updateTrick(Card[] currentTrick);
 
}