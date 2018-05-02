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
  
  /**
   * @author dpervane
   */
  
  @Override
  public Image getImage(String colour, String number) {
    Image img = null;
    try {
      selectCard.setString(1, colour);
      selectCard.setString(2, number);
      selectCard.execute();
      ResultSet rs = selectCard.executeQuery();
      while (rs.next()) {
        InputStream in = rs.getBinaryStream("image");
        img = SwingFXUtils.toFXImage(ImageIO.read(in), null);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return img;
  }
  
  /**
   * @author dpervane
   */
  
  @Override
  public Image getImageDarker(String colour, String number) {
    Image img = null;
    try {
      selectCardDarker.setString(1, colour);
      selectCardDarker.setString(2, number);
      selectCardDarker.execute();
      ResultSet rs = selectCardDarker.executeQuery();
      while (rs.next()) {
        InputStream in = rs.getBinaryStream("image");
        img = SwingFXUtils.toFXImage(ImageIO.read(in), null);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return img;
  }
  
  /**
   * @author dpervane
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
   * @author dpervane
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
   * @author dpervane
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
   * @author dpervane
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
   * @author dpervane
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
   * @author dpervane
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

