package database;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import logic.Player;

public class DatabaseHandler extends database {
  
  private static PreparedStatement insPlayer;
  private static PreparedStatement selectPlayer;
  private static PreparedStatement selectCard;
  private static PreparedStatement deletePlayer;
  private static PreparedStatement editPlayer;
  private Connection c = null;
  
  public DatabaseHandler() {
    super();
    this.c = super.connection;
    this.prepareStatements();
  }
  
  public void prepareStatements() {
    try {
        
        insPlayer = c.prepareStatement("INSERT INTO Player (id, name, score, profilePicture) VALUES (?,'?','?,?);");
              
        selectPlayer = c.prepareStatement("SELECT * FROM Player WHERE (name LIKE '%?%') ORDER BY name;");
        
        selectCard = c.prepareStatement("SELECT * FROM Cards WHERE (colour LIKE '%?%') AND (number LIKE '%?%'");
                
        deletePlayer = c.prepareStatement("DELETE FROM Player WHERE id =?;");        
        
        editPlayer = c.prepareStatement("UPDATE Kontakte SET name = '?', score = ?, profilePicture = ? WHERE name LIKE = '%?%';");
    }
    catch(SQLException e) {
      e.printStackTrace();
    }
  }  
  
  public void insertPlayer(Player player) {
    
    try {
        insPlayer.setString(1, player.getName());
        insPlayer.executeUpdate();

    } catch (SQLException e) {    
        e.printStackTrace();
    }
}
  
  public void selectPlayer(Player player) {
    try {
      selectPlayer.setInt(1, player.getId());
      selectPlayer.executeUpdate();
    }catch (SQLException e) {
      e.printStackTrace();
    }
  } 
  
  public void deletePlayer(Player player) {
    
    try {
        deletePlayer.setInt(1, player.getId());
        deletePlayer.executeUpdate();
    } catch (SQLException e) {       
        e.printStackTrace();
    }
  }
  
  public void editPlayer(Player original, Player neu) {    
    try {
        editPlayer.setString(1, neu.getName());         
        editPlayer.executeUpdate();
}
    catch (SQLException e) {       
      e.printStackTrace();
    }
  }

  
  public Blob getCard(Blob card) {
    try {
      selectCard.setBlob(1, card.getBinaryStream());
      selectCard.execute();
    }
    catch (SQLException e) {       
      e.printStackTrace();
  }
    return card;
}
}


