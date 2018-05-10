package interfaces;

import java.util.List;
import logic.Card;
import logic.GameSettings;
import logic.PlayState;
import logic.Player;
import network.server.Server;

// Logic to Network, implemented by Network
public interface LogicNetwork {

  /**
   * should open a new lobby and return the clients server.
   * 
   * @param player who hosts the game
   * @param gs gameSettings
   * @param comment to the others
   * @return the clients own server
   */
  public Server hostGame(Player player, GameSettings gs, String comment);

  /**
   * should return a list of all the open lobbys.
   * 
   * @return a list of servers
   */
  public List<Server> getServer();

  /**
   * to join a lobby.
   * 
   * @param server the player wants to join
   * @param player who wants to join
   * @return true if it worked
   */
  public boolean joinLobby(Server server, Player player);

  /**
   * sends a chat message to the other clients.
   * 
   * @param message to the others
   */
  public void sendChatMessage(Player player, String message);

  /**
   * sends out, if a player announced kontra.
   * 
   */
  public void sendKontra();

  /**
   * sends out, that a player wants to rekontra.
   * 
   */
  public void sendRekontra();

  /**
   * sends out the game settings to other clients.
   * 
   * @param gs gameSettings
   */
  public void sendGameSettings(GameSettings gs);

  /**
   * sends out that the game has started now.
   * 
   */
  public void startGame();

  /**
   * gives a player his hand cards.
   * 
   * @param player to whom the cards are dealt
   * @param cards for the hand
   * @param ps playState
   */
  public void dealCards(Player player, List<Card> cards, PlayState ps);

  /**
   * sends out the bet of a player.
   * 
   * @param bet of the player
   * @param player who bet
   */
  public void bet(int bet, Player player);

  /**
   * sends out the play state to all other clients.
   * 
   * @param ps playState
   */
  public void sendPlayState(PlayState ps);

  /**
   * lets the clients know, that a card was played.
   * 
   * @param card player
   * @param player who played the card
   */
  public void sendCardPlayed(Card card, Player player);

  /**
   * sends out, that a player left the game.
   */
  void exitGame();
}

