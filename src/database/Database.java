package database;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database {
  
  // This class connects the Database (SQLite) with Java.
  // Available for the connection is the library called JDBC.
  
  /**
   * Build the Connection between database and Java.
   * 
   * @author dpervane
   */
  protected Connection connection;
  Properties properties;
  
  public Database() {
//    this.connect(System.getProperty("user.dir") + System.getProperty("file.separator") + "resources"
//            + System.getProperty("file.separator") + "SkatData.db");
//    this.connect(System.getProperty("user.dir") 
//        + System.getProperty("file.separator") + "SkatData.db");
    
    
//    this.getClass().getResourceAsStream(File.separator + "card.jakasd");
    
    this.connect(getClass().getResource(System.getProperty("file.separator") + "SkatData.db"));
  }
  
  /**
   * Connects Database with Java.
   * 
   * @param file name and path of database         
   * @author dpervane
   */
  private void connect(URL file) {
    try {
      Class.forName("org.sqlite.JDBC");
      this.connection = DriverManager.getConnection("jdbc:sqlite:" + file);     
    } catch (ClassNotFoundException e) {
      e.printStackTrace();     
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
} 
    
  
