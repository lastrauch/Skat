package database;


//import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
    // TODO Auto-generated method stub
    BufferedImage bi = SwingFXUtils.fromFXImage(player.getImage(), null);
    ByteArrayOutputStream baos = null;
    try {
        baos = new ByteArrayOutputStream();
        ImageIO.write(bi, "jpg", baos);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
        try {
            baos.close();
        } catch (Exception e) {
        }
    }
    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    try {
      insertPlayer.setString(1, player.getName());
      insertPlayer.setBlob(3, bais);
      insertPlayer.executeUpdate();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }   
  }
//  
//    try {
//      BufferedImage bi = SwingFXUtils.fromFXImage(player.getImage(), null);
//      ByteArrayOutputStream bas = new ByteArrayOutputStream();
//      
//      try {
//        ImageIO.write(bi,"jpg", bas);
//      } catch (IOException e) {
//        // TODO Auto-generated catch block
//        e.printStackTrace();
//      }
//
//      byte[] bytes = bas.toByteArray();
//      InputStream is = new ByteArrayInputStream(bytes);
//      
//      insertPlayer.setString(1, player.getName());
//      insertPlayer.setBlob(3, is);
//      insertPlayer.executeUpdate();
//
//    } catch (SQLException e) {
//      e.printStackTrace();
//    }
//    System.out.println("New Player: " + player);
//  }
  
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
    ByteArrayOutputStream baos = null;
    try {
        baos = new ByteArrayOutputStream();
        ImageIO.write(bi, "jpg", baos);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
        try {
            baos.close();
        } catch (Exception e) {
        }
    }
    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    try {
      changeImage.setString(1, player.getName());
      changeImage.setBlob(3, bais);
      changeImage.execute();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
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

