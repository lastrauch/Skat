package interfaces;

import java.util.ArrayList;
import logic.Card;
import logic.GameSettings;
import logic.PlayState;
import logic.Position;
import logic.Trick;

//Logic to AI, implemented by AI
public interface LogicAI {

  /**
   * asks the player to play a card
   * 
   */
  public void askToPlayCard();

  /**
   * asks the player if he wants to take up the skat
   * 
   */
  public void askToTakeUpSkat();

  /**
   * supposed to ask the Player if he wants to go with the bet or if he wants to pass like
   * "18 or pass?" (if bet=18) 
   * 
   * @param bet
   */
  public void askForBet(int bet);

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

}
