package interfaces;

import javafx.scene.image.Image;
import logic.Card;
import logic.Player;

// Gui to Database, implemented by Database
public interface GuiData {

  /**
   * returns the image of the given Card
   * 
   * @param card
   * @return
   */
  public Image getImage(Card card);

  /**
   * should return the darker image of the Card
   * 
   * @param card
   * @return
   */
  public Image getImageDarker(Card card);

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
  public boolean checkIfPlayerNew(String playerName);

  /**
   * finds the player with the given name and return him
   * 
   * @param name
   * @return
   */
  public Player getPlayer(String name);

  /**
   * finds the player with the given name and return him
   * 
   * @param id
   * @return
   */
  public Player getPlayer(int id);
  /**
   * deletes the given Player
   * 
   * @param player
   */
  public void deletePlayer(Player player);

  /**
   * changes the players name
   * 
   * @param player
   * @param name
   */
  public void changeName(Player player, String name);

  /**
   * changes the players profile pictue
   * 
   * @param player
   * @param image
   */
  public void changeImage(Player player, Image image);
  
  /**
   * updates the given player
   * @param player
   */
  public void updatePlayer(Player player);
}
