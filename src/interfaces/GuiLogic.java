package interfaces;

import java.awt.List;
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
/**
 * @author Larissa
 *
 */
/**
 * @author Larissa
 *
 */
/**
 * @author Larissa
 *
 */
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
   * returns a new chat message
   * @return
   */
  public String getChatText();
  
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
  public ArrayList<Card> sortHand(PlayState ps, ArrayList<Card> hand); 
  
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
   * return the current player
   * @return
   */
//  public Player getPlayer();
}
