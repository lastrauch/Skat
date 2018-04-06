package database;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class DatabaseHandler extends database {
  
  private static PreparedStatement insPlayer;
  private static PreparedStatement selectPlayer;
  private static PreparedStatement deletePlayer;
  private static PreparedStatement editPlayer;
  private static PreparedStatement selectCards;
  private Connection c = null;
  
  public DatabaseHandler() {
    super();
    this.c = super.connection;
    this.prepareStatements();
  }
  
  public void prepareStatements() {
    try {
        System.out.println("bla1");
        insPlayer = c.prepareStatement(
                "INSERT INTO Player (id, name, nickname, score, profilePicture) VALUES (?,'?','?',?,?);");
        
        
        selectPlayer = c.prepareStatement("SELECT * FROM Player WHERE (nickname LIKE '%?%') ORDER BY nickname;");
        
        selectCards = c.prepareStatement("SELECT * FROM Cards WHERE (colour LIKE '%?%') AND (value LIKE '%?%') AND (type LIKE '%?%'");
                
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

    System.out.println("bla1");

    try {
        insPlayer.setString(1, player.getName());
        insPlayer.setString(2, player.getNickname());
        insPlayer.setInt(3, player.getScore());
        insPlayer.setBlob(4, player.getProfilePicture());
        insPlayer.executeUpdate();

    } catch (SQLException e) {    
        e.printStackTrace();
    }
}
//deletePlayer: Hier soll der übergebene Kontakt aus der Datenbank gelöscht werden.
  
  public void deletePlayer(Player player) {
    System.out.println("bla2");
    try {
        deletePlayer.setInt(1, player.getId());
        deletePlayer.executeUpdate();
    } catch (SQLException e) {       
        e.printStackTrace();
    }
  }
//editPlayer: Hier soll der übergebene Spieler original durch den Spieler neu ersetzt werden. 
//Allerdings soll die ID des Spielers beibehalten werden.
  
  public void editPlayer(Player original, Player neu) {
    System.out.println("bla3");
    try {
        editPlayer.setString(1, neu.getName());
        editPlayer.setString(2, neu.getNickname());
        editPlayer.setInt(3, neu.getScore());
        editPlayer.setBlob(4, neu.getProfilePicture());      
        editPlayer.executeUpdate();
}
    catch (SQLException e) {       
      e.printStackTrace();
    }
  }
  
  public List<Cards> getCards(String type, int value, Blob image ) {
    System.out.println("bla3");

    List<Cards> ausg = new ArrayList<Cards>();

    try {
        String typehilf;
        int id, valuehilf;
        Blob imagehilf;
        Cards hilf;
        
        selectCards.setString(1, type);
        selectCards.setInt(2, value);
        selectCards.setBlob(3, image);
        selectCards.executeUpdate();
        ResultSet rs = selectCards.executeQuery();
        while (rs.next()) {
            id = rs.getInt("id"); 
            valuehilf = rs.getInt("value");
            imagehilf= rs.getBlob("image");
            typehilf = rs.getString("type");            
            hilf = new Cards(id, valuehilf, imagehilf, typehilf);
            ausg.add(hilf);
        }
        rs.close();

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return ausg;
}
        
  
  
  

}
