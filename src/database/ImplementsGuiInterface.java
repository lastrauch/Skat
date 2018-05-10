package database;

import interfaces.GuiData;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import logic.Card;
import logic.Player;

public class ImplementsGuiInterface extends DatabaseHandler implements GuiData {

  // This class implements all requested methods for UI.
  // Avaliable methods are:

  // getImage(String colour, String number) : Image
  // Gives back the image of the Card, which is called by UI

  // getImageDarker(String colour, String number) : Image
  // Gives back the darker version of the Card, which is called by UI

  // insertPlayer(Player player)
  // Stores a new player profile in the database, after creating a new player in UI

  // checkIfPlayerNew(String username) : boolean
  // Returns true if the player is not found in database false if already in database

  // getPlayer(Player player) : Player
  // Finds the player with the given name and return him

  // changeName(String neu, Player original)
  // Changes the players name

  // changeImage(Player player, Image img)
  // Changes the players profile picture

  // getPlayer(String playername): Player
  // Finds the player with the given name and return him

  //////////////////////////////////////////////////////////////////////////////////////////////////
  // Methods called by GuiData
  //////////////////////////////////////////////////////////////////////////////////////////////////

  private List<CardImage> cards;

  public ImplementsGuiInterface() {
    PngReader pr = new PngReader();
    this.cards = pr.readAll();
  }

  private PngReader pr;

  /**
   * returns the image of a card.
   * 
   * @author awesch
   */
  @Override
  public Image getImage(Card card) {
    Image img = null;

    for (CardImage ci : this.cards) {
      if (ci.getCard().equals(card) && !ci.isDark()) {
        return ci.getImg();
      }
    }
    return img;
  }

  /**
   * returns the dark image of a card.
   * 
   * @author awesch
   */
  @Override
  public Image getImageDarker(Card card) {
    Image img = null;

    for (CardImage ci : this.cards) {
      if (ci.getCard().equals(card) && ci.isDark()) {
        return ci.getImg();
      }
    }
    return img;
  }

  /**
   * Stores a new player profile in the database, after creating a new player in UI.
   * 
   * @author dpervane
   * @param player is the new player
   */
  @Override
  public void insertPlayer(Player player) {
    try {
      insertPlayer.setString(1, player.getName());
      insertPlayer.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Returns true if the player is not found in database false if already in database.
   * 
   * @author dpervane
   * @param username of the player
   * @return boolean
   */
  @Override
  public boolean checkIfPlayerNew(String username) throws SQLException {
    try {
      selectPlayerName.setString(1, username);
      ResultSet rs = selectPlayerName.executeQuery();
      if (rs.next()) {
        return false;
      } else {
        return true;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return true;
  }

  /**
   * Finds the player with the given name and return him.
   * 
   * @author dpervane
   * @param player is the player
   * @retrun player
   */
  @Override
  public Player getPlayer(Player player) {
    try {
      selectPlayerName.setString(1, player.getName());
      selectPlayerName.executeQuery();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return player;
  }

  /**
   * Finds the player with the given name and return him.
   * 
   * @author dpervane
   * @param playername is the player
   * @return playername
   */
  @Override
  public Player getPlayer(String playername) {
    Player playerName = null;
    try {
      selectPlayerName.setString(1, playername);
      selectPlayerName.execute();
      ResultSet rs = selectPlayerName.executeQuery();
      while (rs.next()) {
        playerName = new Player(playername);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    System.out.println(playerName);
    return playerName;
  }

  /**
   * Changes the players name.
   *
   * @author dpervane
   * @param neu is the new name of player
   * @param original is the old name of player
   */
  @Override
  public void changeName(String neu, Player original) {
    try {
      changeName.setString(1, neu);
      changeName.setString(2, original.getName());
      changeName.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}


