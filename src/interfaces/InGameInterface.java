package interfaces;

import java.util.ArrayList;
import java.util.List;
import ai.BotDifficulty;
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
  public void startPlay(List<Card> hand, Position position);

  /**
   * should give the player the option to announce kontra and return if he wants to announce it
   * (true) or not (false)
   * 
   * @return
   */
  public boolean askToRekontra();

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
  public boolean askToTakeUpSkat();

  /**
   * supposed to ask the Player if he wants to go with the bet or if he wants to pass like "18 or
   * pass?" (if bet=18)
   * 
   * @param bet
   */
  public boolean askForBet(int bet, Player lastBet);

  /**
   * should reload the hand cards in the given order
   * 
   * @param list
   */
  public void updateHand(List<Card> list);

  /**
   * updates the current trick
   * 
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
   * 
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
   * 
   * @param player1
   * @param player2 is null when declarer won
   */
  public void showWinnerPlay(Player player1, Player player2);

  /**
   * relevant for the ui
   * 
   * @param player
   */
  public void showWinnerGame(Player player);

  /**
   * only relevant for the ui
   * 
   * @param bet
   */
  public void openAskForBet(int bet);

  /**
   * only relevant for the ui
   * 
   * @param bet
   */
  public void updateBet(int bet);

  /**
   * only relevant for the ui
   */
  public void openTakeUpSkat();

  /**
   * only relevant for the ui
   */
  public void openAuctionWinnerScreen();

  /**
   * only relevant for the ui
   * 
   * @param ps
   */
  public void openSwitchSkat(PlayState ps);

  /**
   * relevant for the ai and the ui, returns the two cards so lay on the declarers stack, only if
   * askToTakeUpSkat returns true
   * 
   * @param ps
   * @return
   */
  public List<Card> switchSkat(PlayState ps);

  /**
   * relevant for the ai and the ui, returns the changed PlayState after the Player won the auction
   * 
   * @param ps
   * @return
   */
  public PlayState playsettings(PlayState ps);

  /**
   * especially for the ai to
   * 
   * @param ps
   */
  public void setPlaySettingsAfterAuction(PlayState ps);

}
