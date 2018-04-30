package database;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.imageio.ImageIO;
import interfaces.GuiData;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import logic.Player;

public class ImplementsGuiInterface extends DatabaseHandler implements GuiData {

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

  /* (non-Javadoc)
   * @see interfaces.GuiData#insertPlayer(logic.Player)
   */
  @Override
  public void insertPlayer(Player player) {
    // TODO Auto-generated method stub
    try {
      insertPlayer.setString(1, player.getName());
      insertPlayer.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    }
    System.out.println("New Player: " + player);
  }

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

  /* (non-Javadoc)
   * @see interfaces.GuiData#changeImage(logic.Player, javafx.scene.image.Image)
   */
  @Override
  public void changeImage(Player player, Image image) {
    // TODO Auto-generated method stub
    

    try {
      changeImage.setString(1, player.getName());
      changeImage.setBlob(2, (Blob) image);
      changeImage.executeUpdate();

//      
//      String filename = "C:grey.jpg";
//      File file = new File(filename);
//     FileInputStream fin = new FileInputStream(file);
      
//      ServletFileUpload sfu  = new ServletFileUpload(factory);
//     List items = sfu.parseRequest(request)
//     Image fin = (Image) items.get(2);
//      Blob my_blob = this.connection.createBlob();
//      OutputStream blobwriter = my_blob.setBinaryStream(1);
//      String str = this.readFile(image, my_blob);
      

          
//      changeImage.setString(1, player.getName());
////    changeImage.setBlob(2, image);
//      changeImage.setBlob(2, my_blob );
//      changeImage.executeUpdate();
//      changeImage.setBinaryStream(2, (InputStream) fin, (int)fin.length());
//      int s = changeImage.executeUpdate();
//      if(s>0) {
//        System.out.println("image uploaded successfully");
//        
//      }else {
//        System.out.println("unsucessfull to upload image");
//      }
      
    }
    catch(SQLException e) {
      e.printStackTrace();
    }
  }


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

