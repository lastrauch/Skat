package interfaces;

import java.util.List;
import logic.Card;
import logic.GameSettings;
import logic.PlayState;
import logic.Player;

/**
 * This is an interface between the network and the logic. The networks calls methods on the logic.
 * 
 * @author fkleinoe
 */
public interface NetworkLogic {
  /**
   * Receive lobby information.
   * 
   * @author fkleinoe
   * @param player that are in the lobby
   * @param gameSettings of the game
   */
  void receiveLobby(List<Player> player, GameSettings gameSettings);

  /**
   * Receive gameSettings.
   * 
   * @author fkleinoe
   * @param gameSettings that are updated
   */
  void receiveGameSettings(GameSettings gameSettings);

  /**
   * Receive chat message.
   * 
   * @author fkleinoe
   * @param player wrote a message
   * @param message that was written
   */
  void receiveChatMessage(Player player, String message);

  /**
   * Message that the game was started.
   * 
   * @author fkleinoe
   */
  void receiveStartGame();

  /**
   * Receive the cards that were dealt to this player. The playState is just a dummy to work on
   * later.
   * 
   * @author fkleinoe
   * @param cards that were dealt
   * @param playState dummy
   */
  void receiveCards(List<Card> cards, PlayState playState);

  /**
   * Player placed a bet.
   * 
   * @author fkleinoe
   * @param player that placed the bet
   * @param bet that was placed
   */
  void receiveBet(Player player, int bet);

  /**
   * Someone declared kontra.
   * 
   * @author fkleinoe
   */
  void receiveKontra();

  /**
   * Declarer declared rekontra.
   * 
   * @author fkleinoe
   */
  void receiveRekontra();

  /**
   * Declarer set the playState.
   * 
   * @author fkleinoe
   * @param playState that needs to be updated
   */
  void receivePlayState(PlayState playState);

  /**
   * Player played a card.
   * 
   * @author fkleinoe
   * @param player that played a card
   * @param card that was played
   */
  void receiveCardPlayed(Player player, Card card);

  /**
   * Player disconnected.
   * 
   * @author fkleinoe
   * @param player that disconnected
   */
  void receivePlayerDisconnected(Player player);
}
