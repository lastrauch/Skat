package database;


//import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialException;
import interfaces.GuiData;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import logic.Player;

public class ImplementsGuiInterface extends DatabaseHandler implements GuiData {
  
  /**
   * @author dpervane
   */

  /* (non-Javadoc)
   * @see interfaces.GuiData#getImage(java.lang.String, java.lang.String)
   */
  @Override
  public Image getImage(String colour, String number) {
    // TODO Auto-generated method stub
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

  /* (non-Javadoc)
   * @see interfaces.GuiData#getImageDarker(java.lang.String, java.lang.String)
   */
  @Override
  public Image getImageDarker(String colour, String number) {
    // TODO Auto-generated method stub
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

  /* (non-Javadoc)
   * @see interfaces.GuiData#insertPlayer(logic.Player)
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

  /* (non-Javadoc)
   * @see interfaces.GuiData#checkIfPlayerNew(java.lang.String)
   */
  @Override
  public boolean checkIfPlayerNew(String username) throws SQLException {
    // TODO Auto-generated method stub
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

  /* (non-Javadoc)
   * @see interfaces.GuiData#getPlayer(logic.Player)
   */
  @Override
  public Player getPlayer(Player player) {
    // TODO Auto-generated method stub
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

  /* (non-Javadoc)
   * @see interfaces.GuiData#changeName(java.lang.String, logic.Player)
   */
  @Override
  public void changeName(String neu, Player original) {
    // TODO Auto-generated method stub
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

  /* (non-Javadoc)
   * @see interfaces.GuiData#changeImage(logic.Player, javafx.scene.image.Image)
   */
  @Override
  public void changeImage(Player player, Image img) {
    // TODO Auto-generated method stub
    
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
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    
  }
      
  /**
   * @author dpervane
   */


  @Override
  public Player getPlayer(String playername) {
    // TODO Auto-generated method stub
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

