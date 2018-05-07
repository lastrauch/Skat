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
   * @param player
   * @param gs
   * @param comment
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
   * @param server
   * @param player
   * @return if it worked
   */
  public boolean joinLobby(Server server, Player player);

  /**
   * sends a chat message to the other clients.
   * 
   * @param message
   */
  public void sendChatMessage(String message);
  
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
   * @param gs
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
   * @param player
   * @param cards
   * @param ps
   */
  public void dealCards(Player player, List<Card> cards, PlayState ps);
  
  /**
   * sends out the bet of a player.
   * 
   * @param bet
   * @param player
   */
  public void bet(int bet, Player player);

  /**
   * sends out the play state to all other clients.
   * 
   * @param ps
   */
  public void sendPlayState(PlayState ps);

  /**
   * lets the clients know, tha a card was played.
   * 
   * @param card
   * @param player
   */
  public void sendCardPlayed(Card card, Player player);

  /**
   * sends out, that a player left the game.
   */
  void exitGame();
}

