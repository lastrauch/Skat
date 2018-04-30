package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseHandler extends Database{
  protected static PreparedStatement insertPlayer;
  protected static PreparedStatement selectPlayerName;
  protected static PreparedStatement selectCard;
  protected static PreparedStatement selectCardDarker;
  protected static PreparedStatement changeName;
  protected static PreparedStatement changeImage;
  
  private Connection c = null;
  
  public DatabaseHandler() {
    super();
    this.c = super.connection;
    this.prepareStatements();
  }
  
  public void prepareStatements() {
    try {
        
        insertPlayer = c.prepareStatement("INSERT INTO Player (name, score, profilePicture) VALUES (?,?,?);");    
        
        selectPlayerName = c.prepareStatement("SELECT * FROM Player WHERE (name LIKE ?) ORDER BY name;");
        
        selectCard = c.prepareStatement("SELECT * FROM Cards WHERE (colour LIKE ?) AND (number LIKE ?);");
        
        selectCardDarker = c.prepareStatement("SELECT * FROM CardsDark WHERE (colour LIKE ?) AND (number LIKE ?);");      
        
        changeName = c.prepareStatement("UPDATE Player SET name = ? WHERE (name LIKE ?);");
        
        changeImage = c.prepareStatement("UPDATE Player SET profilePicture = ? WHERE (name LIKE ?);");
        
    }
    catch(SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
