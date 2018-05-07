package database;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import interfaces.GuiData;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
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
  
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // Methods called by UI
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  
  /** 
   * Gives back the image of the Card, which is called by UI
   * 
   * @author dpervane
   * @param colour
   * @param number
   * @return img
   */ 
  @Override
  public Image getImage(String colour, String number) {
    Image img = null;
    try {
      selectCard.setString(1, colour);
      selectCard.setString(2, number);
      ResultSet rs = selectCard.executeQuery();
      while (rs.next()) {
        InputStream in = rs.getBinaryStream("image");
        img = SwingFXUtils.toFXImage(ImageIO.read(in), null);
      }
      rs.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return img;
  }
  
  /**
   * Gives back the darker version of the Card, which is called by UI
   * 
   * @author dpervane
   * @param colour
   * @param number
   * @return img
   */ 
  @Override
  public Image getImageDarker(String colour, String number) {
    Image img = null;
    try {
      selectCardDarker.setString(1, colour);
      selectCardDarker.setString(2, number);
      ResultSet rs = selectCardDarker.executeQuery();
      while (rs.next()) {
        InputStream in = rs.getBinaryStream("image");
        img = SwingFXUtils.toFXImage(ImageIO.read(in), null);
      }
      rs.close();
    } catch (Exception e) {
      e.printStackTrace();
    } 
    return img;
  }
  
  /**
   * Stores a new player profile in the database, after creating a new player in UI
   * 
   * @author dpervane
   * @param player
   */
  @Override
  public void insertPlayer(Player player) {
    try{
      insertPlayer.setString(1, player.getName());
      insertPlayer.executeUpdate();
      
      if(player.getImage() != null){
        changeImage(player, player.getImage());
      }
      
    }catch (SQLException e) {
      e.printStackTrace();
    }
  }  
  
  /**
   * Returns true if the player is not found in database false if already in database
   * 
   * @author dpervane
   * @param username
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
   * Finds the player with the given name and return him
   * 
   * @author dpervane
   * @param player
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
   * Changes the players name
   *
   * @author dpervane
   * @param neu
   * @param original
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
  
  /**
   * Changes the players profile picture
   * 
   * @author dpervane
   * @param player
   * @param img
   */
  @Override
  public void changeImage(Player player, Image img) {    
    BufferedImage bi = SwingFXUtils.fromFXImage(img, null);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    
    try {
      Blob blFile = new javax.sql.rowset.serial.SerialBlob(baos.toByteArray()); 
      ImageIO.write(bi, ".jpg", baos);
      InputStream is = new ByteArrayInputStream(baos.toByteArray());
    
      changeImage.setString(2, player.getName());
      changeImage.setBlob(1, blFile);
      changeImage.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (IOException e1) {
      e1.printStackTrace();
    }
  }
      
  /**
   * Finds the player with the given name and return him
   * 
   * @author dpervane
   * @param playername
   * @return playername
   */
  @Override
  public Player getPlayer(String playername) {
    Player playerName = null;
    try {     
      selectPlayerName.setString(1, playername);
      selectPlayerName.execute();
      ResultSet rs = selectPlayerName.executeQuery(); 
      while(rs.next()) {
      playerName = new Player(playername);
      }
    }
    catch(SQLException e) {
      e.printStackTrace();
      
    }System.out.println(playerName);
   return playerName;
  }
}

