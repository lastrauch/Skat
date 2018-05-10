package interfaces;

import java.util.List;
import logic.Card;
import logic.GameSettings;
import logic.PlayState;
import logic.Player;
import logic.Position;


public interface InGameInterface {

  /**
   * should open the InGameScreen and show the Player his cards and his position.
   * 
   * @param hand of the player
   * @param position of the player
   */
  public void startPlay(List<Card> hand, Position position);

  /**
   * should give the player the option to announce rekontra.
   * 
   */
  public void askToRekontra();

  /**
   * only relevant for the ui, tells the player he's supposed to play a card, used before
   * askToPlayCard.
   */
  public void itsYourTurn();

  /**
   * asks the player to play a card.
   * @param timeToPlay Time the player has to play a card.
   * @param ps PlayState
   * @return int the played card
   */
  public int askToPlayCard(int timeToPlay, PlayState ps);


  /**
   * asks the player if he wants to take up the skat.
   * @return if the player wants to take up the skat.
   */
  public boolean askToTakeUpSkat();

  /**
   * supposed to ask the Player if he wants to go with the bet or if he wants to pass like "18 or
   * pass?" (if bet=18).
   * 
   * @param bet the player bet
   * @param last bet a player made.
   * @return if the player wants to bet the bet
   */
  public boolean askForBet(int bet, Player lastBet);

  /**
   * updates the last bet (you don't need to be part of the conversation).
   * 
   * @param bet which was bet
   * @param player who bet
   */
  public void receivedNewBet(int bet, Player player);

  /**
   * should reload the hand cards in the given order.
   * 
   * @param list new hand
   */
  public void updateHand(List<Card> list);

  /**
   * updates the current trick with the last played card and the Player, who played it.
   * 
   * @param card which was just played
   * @param player who played it
   */
  public void receivedNewCard(Card card, Player player);

  /**
   * sets the gameSettings, before game starts.
   * 
   * @param gs GameSettings
   */
  public void setGameSettings(GameSettings gs);

  /**
   * stops the game and tells why.
   * 
   * @param reason why the game is supposed to stop
   */
  public void stopGame(String reason);

  /**
   * especially important for ai, shows trick winner.
   * 
   * @param player who won the last trick
   */
  public void showWinnerTrick(Player player);

  /**
   * shows the points of all players.
   * 
   * @param player s to extract their points
   */
  public void showScore(List<Player> player);

  /**
   * only relevant for the ui.
   * 
   * @param bet which will be asked
   */
  public void openAskForBet(int bet);

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
   * @param ps PlayState to get skat
   */
  public void openSwitchSkat(PlayState ps);

  /**
   * relevant for the ai and the ui, returns the two cards so lay on the declarers stack, only if
   * askToTakeUpSkat returns true.
   * 
   * @param ps PlayState
   * @return layedDownCards
   */
  public List<Card> switchSkat(PlayState ps);

  /**
   * relevant for the ai and the ui, returns the changed PlayState after the Player won the auction.
   * 
   * @param ps PlayState
   * @return PlayState
   */
  public PlayState askToSetPlayState(PlayState ps);

  /**
   * especially for the ai to know which PlayMode is played.
   * 
   * @param ps PlayState
   */
  public void setPlaySettingsAfterAuction(PlayState ps);


  /**
   * Example: Clubs-Jack, Spades-Jack, Clubs-10, Clubs-8, Hearts-7, Diamonds-9 Playable: Trump (Jack
   * + Clubs) Return Array: (null, null, null, null, Hearts-7, Diamonds-9).
   * 
   * @param cards with null, where not possible
   */
  public void showPossibleCards(List<Card> cards);

  /**
   * should show the cards of the given player, who plays open.
   * 
   * @param player who plays open
   */
  public void showOpen(Player player);

}
