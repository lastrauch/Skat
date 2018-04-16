package interfaces;

import java.util.ArrayList;
import logic.Card;
import logic.PlayState;
import logic.Trick;

public interface InGameInterface {

  /**
   * asks the player to play a card, returns the index of the chosen card
   * 
   * @return
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
   * @return
   */
  public boolean takeUpSkat();

  /**
   * should ask the player to put down two cards (after updated hand of 12 cards) and returns the
   * index of the two chosen cards (from left to right / low to high)
   * 
   * @return
   */
  public int[] askToTakeDownTwoCards();

  /**
   * supposed to ask the Player if he wants to go with the bet or if he wants to pass like
   * "18 or pass?" (if bet=18) returns false if he wants to pass returns true if he clicks on 18
   * belongs to the methods pass and bet
   * 
   * @param bet
   * @return
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
   * should show the trick in the middle of the table (could just contain one card) trick has
   * getters for every Card or just the whole Card Array
   * 
   * @param trick
   */
  public void showTrick(Trick trick);
}
