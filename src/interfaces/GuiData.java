package interfaces;

import java.sql.SQLException;
import javafx.scene.image.Image;
import logic.Card;
import logic.Player;

// Gui to Database, implemented by Database
public interface GuiData {

  /**
   * Returns the image of the given Card.
   * 
   * @author dpervane
   * @param colour of the card
   * @param number of the card
   * @return img
   */
  public Image getImage(Card card);

  /**
   * Should return the darker image of the Card.
   * 
   * @author dpervane
   * @param colour of the card
   * @param number of the card
   * @return img
   */
  public Image getImageDarker(Card card);

  /**
   * Saves a player profile in the Database.
   * 
   * @author dpervane
   * @param player is the new player
   */
  public void insertPlayer(Player player);

  /**
   * Returns true if the player is not found in database false if already in database.
   * 
   * @author dpervane
   * @param username of the Player
   * @return boolean
   */
  public boolean checkIfPlayerNew(String username) throws SQLException;

  /**
   * Finds the player with the given name and return him.
   * 
   * @author dpervane
   * @param player is the player
   * @return
   */
  public Player getPlayer(Player player);
  
  /**
   * Finds the player with the given name and return him.
   * 
   * @author dpervane
   * @param playername is the player
   * @return playername
   */
  
  public Player getPlayer(String playername);

  /**
   * changes the players name.
   * 
   * @param neu is the new name of player
   * @param original is the old name of player
   */
  public void changeName(String neu, Player original);

}
