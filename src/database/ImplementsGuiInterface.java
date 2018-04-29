package database;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import interfaces.GuiData;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import logic.Player;

public class ImplementsGuiInterface extends DatabaseHandler implements GuiData {

  @Override
  public Image getImage(String colour, String number) {
    // TODO Auto-generated method stub

    Image img = null;
    try {    
      selectCard.setString(1, colour);
      selectCard.setString(2, number);
      selectCard.execute();
      ResultSet rs = selectCard.executeQuery();
      while(rs.next()) {
        InputStream in = rs.getBinaryStream("image");    
//        img = ImageIO.read(in);
        img = SwingFXUtils.toFXImage(ImageIO.read(in), null);
        }
      } catch (Exception e) {       
        e.printStackTrace();
      }    System.out.println("ImageCard");
    return img;
  }

  @Override
  public Image getImageDarker(String colour, String number) {
    // TODO Auto-generated method stub
    System.out.println("ImageDarker");
    Image img = null;
    try {
      selectCardDarker.setString(1, colour);
      selectCardDarker.setString(2, number);
      selectCardDarker.execute();
      ResultSet rs = selectCardDarker.executeQuery();
      while(rs.next()) {
      InputStream in = rs.getBinaryStream("image");    
      img = SwingFXUtils.toFXImage(ImageIO.read(in), null);
    }
      } catch (Exception e) {       
        e.printStackTrace();
      } 
    return img;
  }

  @Override
  public void insertPlayer(Player player) {
    // TODO Auto-generated method stub
    
    try {
      insertPlayer.setString(1, player.getName());
      insertPlayer.executeUpdate();

  } catch (SQLException e) {    
      e.printStackTrace();
  }
    System.out.println("Spieler: " + player + "wurde neu hinzugefügt");
    
  }

  @Override
  public boolean checkIfPlayerNew(String username){
//     TODO Auto-generated method stub
    try {
      selectPlayerName.setString(1, username);
      ResultSet rs = selectPlayerName.executeQuery();
      if(rs.next()) {
//        System.out.println("Player already exists");
        return false;
      }else{
//        System.out.println("Player is new");
        return true;
      }
    }catch(SQLException e) {
        e.printStackTrace();
      }
    return true;
    }
    

  @Override
  public Player getPlayer(Player player) {
    // TODO Auto-generated method stub
   
    try {
      selectPlayerName.setString(1, player.getName());
      selectPlayerName.executeQuery();
      } catch (SQLException e) {
      e.printStackTrace();
    }
    System.out.println(player +  " wird ausgegeben");
  
    return player;
  } 

  @Override
  public void changeName(String neu, Player original) {
    // TODO Auto-generated method stub
    try {
      changeName.setString(1, neu);
      changeName.setString(2, original.getName());      
      changeName.executeUpdate();
      
      } catch (SQLException e) {       
    e.printStackTrace();
  }System.out.println("changeName");
    
  }

  @Override
  public void changeImage(Player player, Image image) {
    // TODO Auto-generated method stub
        
    try {
      Image img = null;
      changeImage.setString(1, player.getName());
//      changeImage.setBlob(2, image.getString());
      changeImage.execute();
      ResultSet rs = changeImage.executeQuery();
      while(rs.next()) {
        InputStream in = rs.getBinaryStream("profilePicture");
        img = SwingFXUtils.toFXImage(ImageIO.read(in), null);
      }
    } catch (Exception e) {       
      e.printStackTrace();
        

    }
  }
}


