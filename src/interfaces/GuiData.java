package interfaces;

import java.awt.image.BufferedImage;
import java.sql.SQLException;
import javafx.scene.image.Image;
import logic.Player;

// Gui to Database, implemented by Database
public interface GuiData {

  /**
   * returns the image of the given Card
   * 
   * @param colour
   * @param number
   * @return
   */
  public Image getImage(String colour, String number);

  /**
   * should return the darker image of the Card
   * 
   * @param colour
   * @param number
   * @return
   */
  public Image getImageDarker(String colour, String number);

  /**
   * saves a player profile in the Database
   * 
   * @param player
   */
  public void insertPlayer(Player player);

  /**
   * returns true if the player is not found in database false if already in database
   * 
   * @return
   */
  public boolean checkIfPlayerNew(String username) throws SQLException;

  /**
   * finds the player with the given name and return him
   * 
   * @param player
   * @return
   */
  public Player getPlayer(Player player);

  /**
   * Brauch ich gar nicht
   */
//  /**
//   * deletes the given Player
//   * 
//   * @param player
//   */
//  public void deletePlayer(Player player);

  /**
   * changes the players name
   * 
   * @param neu
   * @param original
   */
  public void changeName(Player neu, Player original);

  /**
   * changes the players profile pictue
   * 
   * @param player
   * @param image
   */
  public void changeImage(Player player, Image image);
  
  /**
   * Das musst du ja von dir aus machen
   * ich brauche nur die beiden Methoden obendrüber
   */
//  /**
//   * updates the given player
//   * @param player
//   */
//  public void updatePlayer(Player player);
}
