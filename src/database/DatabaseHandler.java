package database;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logic.Player;
import logic.Card;



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
        
        insPlayer = c.prepareStatement(
                "INSERT INTO Player (id, name, nickname, score, profilePicture) VALUES (?,'?','?',?,?);");
        
        
        selectPlayer = c.prepareStatement("SELECT * FROM Player WHERE (nickname LIKE '%?%') ORDER BY nickname;");
        
        selectCard = c.prepareStatement("SELECT * FROM Cards WHERE (colour LIKE '%?%') AND (value LIKE '%?%') AND (type LIKE '%?%'");
                
        deletePlayer = c.prepareStatement("DELETE FROM Player WHERE id =?;");        
        
        editPlayer = c.prepareStatement(
            "UPDATE Kontakte SET name = '?', nickname = '?', score = ?, profilePicture = ? WHERE name LIKE = '%?%';");
    }
    catch(SQLException e) {
      e.printStackTrace();
    }
  }
  
//  insertPlayer: Hier soll ein als Parameter übergebener Spieler in die Datenbank eingetragen werden. 
  
  public void insertPlayer(Player player) {
    
    try {
        insPlayer.setString(1, player.getName());
        //insPlayer.setString(2, player.getNickname());
        //insPlayer.setInt(3, player.getScore());
        //insPlayer.setBlob(4, player.getProfilePicture());
        insPlayer.executeUpdate();

    } catch (SQLException e) {    
        e.printStackTrace();
    }
}
  
//deletePlayer: Hier soll der übergebene Kontakt aus der Datenbank gelöscht werden.
  
  public void deletePlayer(Player player) {
    
    try {
        deletePlayer.setInt(1, player.getId());
        deletePlayer.executeUpdate();
    } catch (SQLException e) {       
        e.printStackTrace();
    }
  }
  
//editPlayer: Hier soll der übergebene Spieler original durch den Spieler neu ersetzt werden. 
  
  public void editPlayer(Player original, Player neu) {
    
    try {
        editPlayer.setString(1, neu.getName());
        //editPlayer.setString(2, neu.getNickname());
        //editPlayer.setInt(3, neu.getScore());
        //editPlayer.setBlob(4, neu.getProfilePicture());      
        editPlayer.executeUpdate();
}
    catch (SQLException e) {       
      e.printStackTrace();
    }
  }
  
  public List<Card> getCard(String type, int value, Blob image ) {

    List<Card> ausg = new ArrayList<Card>();

    try {
        String typehilf;
        int id, valuehilf;
        Blob imagehilf;
        Card hilf;
        
        selectCard.setString(1, type);
        selectCard.setInt(2, value);
        selectCard.setBlob(3, image);
        selectCard.executeUpdate();
        ResultSet rs = selectCard.executeQuery();
        while (rs.next()) {
            id = rs.getInt("id"); 
            valuehilf = rs.getInt("value");
            imagehilf= rs.getBlob("image");
            typehilf = rs.getString("type");            
            //hilf = new Card(id, valuehilf, imagehilf, typehilf);
           //ausg.add(hilf);
        }
        rs.close();

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return ausg;
}
        
  
  
  

}
