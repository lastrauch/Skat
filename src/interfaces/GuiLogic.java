package interfaces;

import java.util.List;
import java.util.ArrayList;
import ai.BotDifficulty;
import javafx.scene.image.Image;
import logic.Card;
import logic.GameMode;
import logic.GameSettings;
import logic.PlayState;
import logic.Player;
import network.server.Server;

// GUI to Logic, implemented by Logic
public interface GuiLogic {

  /**
   * The user starts the program and logs in with a username.
   * @param username of the user
   */
  public void login(String username);
  
  /**
   * So the logic knows the new username, in case it has changed.
   * 
   * @author lstrauch
   * @param username that has to be set.
   */
  public void setUsername(String username);
  
  
  /**
   * Should delete an already existing bot if difficulty has changed.
   * @param botname of the bot that should be deleted.
   */
  public void deleteBot(String botname); 
  
  /**
   * Should create a new bot with the set difficulty.
   * @param botname of the bot that shoult be setted.
   * @param difficulty of the (new) bot.
   */
  
  public void setBot(String botname, BotDifficulty difficulty);
  
  /**
   * send the just tipped chat message.
   * @param message the over players get.
   */
  public void sendChatText(String message);
  
  /**
   * The client joins a lobby.
   * @param ip of the server, the client wants to join.
   */
  public void joinGame(String ip); 
  
  /**
   * A new skat server is started.
   * @param comment the host uses to describe his/her server.
   */
  public void hostGame(String comment, GameSettings gs);
  
  /**
   * supposed to sort the cards from jack to trump to lowest value.
   * @param ps PlayState
   * @return the sorted array of cards
   */
  public ArrayList<Card> sortHand(PlayState ps, List<Card> cardlist); 
  
  /**
   * starts the game after SinglePlayer Lobby.
   * @param gs GameSettings the Host sends to all clients.
   */
  public void startGame(GameSettings gs);
  
  /**
   * should return how many players are in one server, what's the server's name and his message.
   * @return List of server
   */
  public ArrayList<Server> lobbyInformation();
  
  /**
   * returns the current player.
   * @return player
   */
  public Player getPlayer();
  
  /**
   * announces kontra after beeing clicked.
   * @author lstrauch
   */
  public void announceContra();
  
  /**
   * announces rekontra after being clicked.
   */
  public void announceRekontra();
}
