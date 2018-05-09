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
   * 
   * @param m
   */
  public void decideGameMode(GameMode m);

  /**
   * 
   * @param username
   */
  public void login(String username, Image profilepicture);
  
  /**
   * So the logic knows the new username, in case it has changed
   * 
   * @author lstrauch
   * @param username
   */
  public void setUsername(String username);
  
  
  /**
   * Should delete an already existing bot if difficulty has changed
   * @param botname
   */
  public void deleteBot(String botname); 
  
  /**
   * Should create a new bot with the set difficulty
   * @param botname
   * @param difficulty
   */
  public void setBot(String botname, BotDifficulty difficulty);
  
  /**
   * send the just tipped chat message
   * @param message
   */
  public void sendChatText(String message);
  
  /**
   * @param hostName
   */
  public void joinGame(String serverName); 
  
  /**
   * @param comment
   */
  public void hostGame(String comment, GameSettings gs);
  
  /**
   * supposed to  skat the cards from jack to trump to lowest value
   * @return
   */
  public ArrayList<Card> sortHand(PlayState ps, List<Card> cardlist); 
  
  /**
   * starts the game afte SinglePlayer Lobby
   * @param gs
   */
  public void startGame(GameSettings gs);
  
  /**
   * should return how many players are in one server, what's the server's name and his message
   * @return
   */
  public ArrayList<Server> lobbyInformation();
  
  /**
   * returns the current player
   * @return
   */
  public Player getPlayer();
  
  /**
   * announces kontra after beeing clicked
   * @author lstrauch
   */
  public void announceContra();
  
  /**
   * announces rekontra after being clicked.
   */
  public void announceRekontra();
}
