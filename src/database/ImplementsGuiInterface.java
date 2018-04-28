package database;

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
      }
    return img;
  }

  @Override
  public Image getImageDarker(String colour, String number) {
    // TODO Auto-generated method stub
    Image img = null;
    try {
      selectCardDarker.setString(1, colour);
      selectCardDarker.setString(2, number);
      selectCardDarker.execute();
      ResultSet rs = selectCardDarker.executeQuery();
      while(rs.next()) {
      InputStream in = rs.getBinaryStream("image_Dark");    
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
    
  }

  @Override
  public boolean checkIfPlayerNew() throws SQLException {
    // TODO Auto-generated method stub
    int c = 0;
    ResultSet rs = countPlayer.executeQuery();
    c = rs.getInt(1);
    if(c<=0) {
      return true;
      }else {
    return false;
      }
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
    return player;
  }

  @Override
  public void deletePlayer(Player player) {
    // TODO Auto-generated method stub
    try {
      deletePlayer.setInt(1, player.getId());
      deletePlayer.executeUpdate();
  } catch (SQLException e) {       
      e.printStackTrace();
  }
  }

  @Override
  public void changeName(Player neu, Player original) {
    // TODO Auto-generated method stub
    try {
      changeName.setString(1, neu.getName());
      changeName.setString(2, original.getName());      
      changeName.executeUpdate();
      
      } catch (SQLException e) {       
    e.printStackTrace();
  }
    
  }

  @Override
  public void changeImage(Player player, Image image) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void updatePlayer(Player player) {
    // TODO Auto-generated method stub
    
  }
  

}
