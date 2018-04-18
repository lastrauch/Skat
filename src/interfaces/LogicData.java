package interfaces;

import javafx.scene.image.Image;
import logic.Card;
import logic.Player;

// Logic to Database, implemented by Database
public interface LogicData {

  /**
   * should return the image of the given Card
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
   * should save a player profile in the Database
   * 
   * @param player
   */
  public void insertPlayer(Player player);

  /**
   * should return true if the player is not found in database false if already in database
   * 
   * @return
   */
  public boolean checkIfPlayerNew(String playerName);

  /**
   * should find the player with the given name and return him
   * 
   * @param name
   * @return
   */
  public Player getPlayer(String name);

  /**
   * should delete the given Player
   * 
   * @param player
   */
  public void deletePlayer(Player player);

  /**
   * should change the players name
   * 
   * @param player
   * @param name
   */
  public void changeName(Player player, String name);

  /**
   * should change the players profile pictue
   * 
   * @param player
   * @param image
   */
  public void changeImage(Player player, Image image);
}
