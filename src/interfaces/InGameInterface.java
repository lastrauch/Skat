package interfaces;

import logic.Card;
import logic.GameSettings;
import logic.PlayState;
import logic.Player;
import logic.Position;
import java.util.List;

public interface InGameInterface {

  /**
   * should open the InGameScreen and show the Player his cards and his position.
   * 
   * @param hand
   * @param position
   */
  public void startPlay(List<Card> hand, Position position);

  /**
   * should give the player the option to announce kontra and return if he wants to announce it.
   * (true) or not (false)
   * 
   * @return
   */
  public boolean askToRekontra();

  /**
   * only relevant for the ui, tells the player he's supposed to play a card, used before
   * askToPlayCard.
   */
  public void itsYourTurn();

  /**
   * asks the player to play a card.
   * 
   */
  public int askToPlayCard();

  /**
   * should show the number "seconds" at the corner of the screen.
   * 
   * @param seconds
   */
  public void showSecondsLeftToPlayCard(int seconds);

  /**
   * asks the player if he wants to take up the skat.
   * 
   */
  public boolean askToTakeUpSkat();

  /**
   * supposed to ask the Player if he wants to go with the bet or if he wants to pass like "18 or
   * pass?" (if bet=18).
   * 
   * @param bet
   */
  public boolean askForBet(int bet, Player lastBet);

  /**
   * updates the last bet (you don't need to be part of the conversation).
   * 
   * @param bet
   * @param player
   */
  public void receivedNewBet(int bet, Player player);

  /**
   * should reload the hand cards in the given order.
   * 
   * @param list
   */
  public void updateHand(List<Card> list);

  /**
   * updates the current trick with the last played card and the Player, who played it.
   * 
   * @param currentTrick
   */
  public void receivedNewCard(Card card, Player player);

  /**
   * sets the gameSettings, before game starts.
   * 
   * @param gs
   */
  public void setGameSettings(GameSettings gs);

  /**
   * stops the game and tells why.
   * 
   * @param reason
   */
  public void stopGame(String reason);

  /**
   * especially important for ai, shows trick winner.
   * 
   * @param player
   */
  public void showWinnerTrick(Player player);

  /**
   * winner of play/round.
   * 
   * @param player1
   * @param player2 is null when declarer won
   */
  public void showWinnerPlay(Player player1, Player player2);

  /**
   * relevant for the ui.
   * 
   * @param player
   */
  public void showWinnerGame(Player player);

  /**
   * only relevant for the ui.
   * 
   * @param bet
   */
  public void openAskForBet(int bet);

  /**
   * only relevant for the ui.
   * 
   * @param bet
   */
  public void updateBet(int bet);

  /**
   * only relevant for the ui.
   */
  public void openTakeUpSkat();

  /**
   * only relevant for the ui.
   */
  public void openAuctionWinnerScreen();

  /**
   * only relevant for the ui.
   * 
   * @param ps
   */
  public void openSwitchSkat(PlayState ps);

  /**
   * relevant for the ai and the ui, returns the two cards so lay on the declarers stack, only if
   * askToTakeUpSkat returns true.
   * 
   * @param ps
   * @return layedDownCards
   */
  public List<Card> switchSkat(PlayState ps);

  /**
   * relevant for the ai and the ui, returns the changed PlayState after the Player won the auction.
   * 
   * @param ps
   * @return newPlayState
   */
  public PlayState askToSetPlayState(PlayState ps);

  /**
   * especially for the ai to know which PlayMode is played.
   * 
   * @param ps
   */
  public void setPlaySettingsAfterAuction(PlayState ps);


  /**
   * 
   * Example: Clubs-Jack, Spades-Jack, Clubs-10, Clubs-8, Hearts-7, Diamonds-9 Playable: Trump (Jack
   * + Clubs) Return Array: (null, null, null, null, Hearts-7, Diamonds-9).
   * 
   * 
   * @author lstrauch
   * @param cards
   */
  public void showPossibleCards(List<Card> cards);

}
