package interfaces;

import java.util.ArrayList;
import java.util.List;
import logic.Card;
import logic.GameSettings;
import logic.PlayState;
import logic.Player;
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
   * @param list
   */
  public void updateHand(List<Card> list);

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
  public void updateTrick(List<Card> currentTrick);
 
  /**
   * 
   * @param gs
   */
  public void setGameSettings(GameSettings gs);
  
  /**
   * stops the game and tells why
   * @param reason
   */
  public void stopGame(String reason);
  
  /**
   * 
   * @param player
   */
  public void showWinnerTrick(Player player);
  
  /**
   * winner of play/round
   * @param player1
   * @param player2 is null when declarer won
   */
  public void showWinnerPlay(Player player1, Player player2);
  
  /**
   * 
   * @param player
   */
  public void showWinnerGame(Player player);
}
