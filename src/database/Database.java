package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database {
  
  // This class connects the Database (SQLite) with Java.
  // Available for the connection is the library called JDBC.
  
  /**
   * @author dpervane
   */
  protected Connection connection;
  Properties properties;
  
  public Database() {
    this.connect(System.getProperty("user.dir") + System.getProperty("file.separator") + "resources"
            + System.getProperty("file.separator") + "SkatData.db");
  }
  
  /**
   * Connects Database with Java
   * 
   * @param file
   *            Name und Pfad der Datenbank
   * @author dpervane
   */
  
  private void connect(String file) {
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