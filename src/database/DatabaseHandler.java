package database;

import java.awt.Image;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import logic.Card;
import logic.Player;


public class DatabaseHandler extends database {
  
  protected static PreparedStatement insertPlayer;
  protected static PreparedStatement selectPlayerName;
  protected static PreparedStatement selectPlayerId;
  protected static PreparedStatement selectCard;
  protected static PreparedStatement selectCardDarker;
  protected static PreparedStatement deletePlayer;
  protected static PreparedStatement changeName;
  protected static PreparedStatement changeImage;
  protected static PreparedStatement checkPlayer;
  protected static PreparedStatement countPlayer;
  
  private Connection c = null;
  
  public DatabaseHandler() {
    super();
    this.c = super.connection;
    this.prepareStatements();
  }
  
  public void prepareStatements() {
    try {
        
        insertPlayer = c.prepareStatement("INSERT INTO Player (id, name, score, profilePicture) VALUES (?,'?',?,?);");    
        
        selectPlayerName = c.prepareStatement("SELECT * FROM Player WHERE (name LIKE '%?%') ORDER BY name;");
        
        selectPlayerId = c.prepareStatement("SELECT * FROM Player WHERE (id LIKE '%?%') ORDER BY id;");
        
        selectCard = c.prepareStatement("SELECT * FROM Cards WHERE (colour LIKE '%?%') AND (number LIKE '%?%');");
        
        selectCardDarker = c.prepareStatement("SELECT * FROM CardsDark WHERE (colour LIKE '%?%') AND (number LIKE '%?%');");
                
        deletePlayer = c.prepareStatement("DELETE FROM Player WHERE (id =?);");        
        
        countPlayer = c.prepareStatement("SELECT COUNT (name) FROM Player;");
        
        changeName = c.prepareStatement("UPDATE Player SET name = '?' WHERE name LIKE = '%?%';");
        
        changeImage = c.prepareStatement("UPDATE Player SET profilePicture = ? WHERE profilePicture LIKE = '%?%';");
        
    }
    catch(SQLException e) {
      e.printStackTrace();
    }
  }
}
  

//  public void insertPlayer(Player player) {
//    
//    try {
//        insertPlayer.setString(1, player.getName());
//        insertPlayer.executeUpdate();
//
//    } catch (SQLException e) {    
//        e.printStackTrace();
//    }
//    System.out.println("insert new Player");
//}
//
//  
//  public void deletePlayer(Player player) {
//   
//    try {
//        deletePlayer.setInt(1, player.getId());
//        deletePlayer.executeUpdate();
//    } catch (SQLException e) {       
//        e.printStackTrace();
//    }
//    System.out.println("delete Player");
//  }
//  
//  public Player getPlayer(Player player) {
//    try {
//      selectPlayerName.setString(1, player.getName());
//      selectPlayerName.executeUpdate();
//      } catch (SQLException e) {
//      e.printStackTrace();
//    }
//    return player;
//  } 
//  
//  public boolean checkIfPlayerNew() throws SQLException {
//    int c = 0;
//    ResultSet rs = countPlayer.executeQuery();
//    c = rs.getInt(1);
//    if(c<=0) {
//      return true;
//      }else {
//        return false;
//        }
//    }  
//  
//  public void selectPlayer(Player player) {
//    try {
//      selectPlayer.setInt(1, player.getId());
//      selectPlayer.executeUpdate();
//    }catch (SQLException e) {
//      e.printStackTrace();
//    }
//  }
//  
//  public void changeName(Player original, Player neu) {    
//    try {
//        changeName.setString(1, neu.getName());
//        changeName.setString(2, original.getName());      
//        changeName.executeUpdate();
//        
//        } catch (SQLException e) {       
//      e.printStackTrace();
//    }
//  }
//
//  

  

