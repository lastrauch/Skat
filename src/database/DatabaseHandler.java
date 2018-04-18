package database;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import logic.Card;
import logic.Player;

public class DatabaseHandler extends database {
  
  private static PreparedStatement insertPlayer;
  private static PreparedStatement selectPlayer;
  private static PreparedStatement selectCard;
  private static PreparedStatement selectCardDarker;
  private static PreparedStatement deletePlayer;
  private static PreparedStatement changeName;
  private static PreparedStatement changeImage;
  private static PreparedStatement checkPlayer;
  private static PreparedStatement countPlayer;
  private Connection c = null;
  
  public DatabaseHandler() {
    super();
    this.c = super.connection;
    this.prepareStatements();
  }
  
  public void prepareStatements() {
    try {
        
        insertPlayer = c.prepareStatement("INSERT INTO Player (id, name, score, profilePicture) VALUES (?,'?',?,?);");
              
        selectPlayer = c.prepareStatement("SELECT * FROM Player WHERE (name LIKE '%?%') ORDER BY name;");
        
        selectCard = c.prepareStatement("SELECT * FROM Cards WHERE (colour LIKE '%?%') AND (number LIKE '%?%');");
        
        selectCardDarker = c.prepareStatement("SELECT * FROM CardsDark WHERE (colour LIKE '%?%') AND (number LIKE '%?%');");
                
        deletePlayer = c.prepareStatement("DELETE FROM Player WHERE (id = ?);"); 
        
        countPlayer = c.prepareStatement("SELECT COUNT (name) FROM Player;");
        
        changeName = c.prepareStatement("UPDATE Player SET name = '?' WHERE name LIKE = '%?%';");
        
        changeImage = c.prepareStatement("UPDATE Player SET profilePicture = ? WHERE profilePicture LIKE = '%?%';");
        
        
    }
    catch(SQLException e) {
      e.printStackTrace();
    }
  }  
  
  public void insertPlayer(Player player) {    
    try {
        insertPlayer.setString(1, player.getName());
        insertPlayer.executeUpdate();
        } catch (SQLException e) { 
          e.printStackTrace();
          }
    }
  
  public Player getPlayer(Player player) {
    try {
      selectPlayer.setString(1, player.getName());
      selectPlayer.executeUpdate();
      } catch (SQLException e) {
      e.printStackTrace();
    }
    return player;
  } 
  
  public boolean checkIfPlayerNew() throws SQLException {
    int c = 0;
    ResultSet rs = countPlayer.executeQuery();
    c = rs.getInt(1);
    if(c<=0) {
      return true;
      }else {
        return false;
        }
    }        
  
//  public static boolean checkIfPlayerNew(ResultSet rs, String name) throws SQLException {
//    ResultSetMetaData rsmd = rs.getMetaData();
//    int columns = rsmd.getColumnCount();
//    for (int x = 1; x <= columns; x++) {
//        if (name.equals(rsmd.getColumnName(x))) {
//            return true;
//        }
//    }
//    return false;
//}
//  public boolean checkDB() {
//    String path = DB_PATH + DB_NAME;
//    File file = new File(path);
//    if (file.exists()) {
//        return true;
//    } else
//        return false;
//}
  
  public void deletePlayer(Player player) {   
    try {
        deletePlayer.setInt(1, player.getId());
        deletePlayer.executeUpdate();
        } catch (SQLException e) {
          e.printStackTrace();
          }
    }
  
  public void changeName(Player original, Player neu) {    
    try {
        changeName.setString(1, neu.getName());
        changeName.setString(2, original.getName());      
        changeName.executeUpdate();
        
        } catch (SQLException e) {       
      e.printStackTrace();
    }
  }

  public Image getImage(Card card) {
    try {
      selectCard.setBlob(1, card.getImage());
      selectCard.execute();
      } catch (SQLException e) {       
        e.printStackTrace();
      } 
    return card;
      }
  
  public void changeImage(Player player, Player image) {
    try {
      changeImage.setBlob(1, image.getImage());
      changeImage.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    }

  public Image getImageDarker(Card card) {
    try {
      selectCardDarker.setBlob(1, card.getImage());
      selectCardDarker.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return card;
  }
}
// Read the blob data as byte

//  public byte[] getImage(String name) {
//  byte[] data = null;
//  Cursor cursor = database.rawQuery("SELECT image FROM places WHERE name = ?", new String[]{name});
//  cursor.moveToFirst();
//  while (!cursor.isAfterLast()) {
//      data = cursor.getBlob(0);
//      break;  // Assumption: name is unique
//  }
//  cursor.close();
//  return data;
//}
//}



