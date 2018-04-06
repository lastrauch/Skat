package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class database {
	
protected Connection connection;
  
  public database() {   
    this.connect(System.getProperty("user.dir") + System.getProperty("file.separator") + "resources"
        + System.getProperty("file.separator") + "Cards.db");
        
  }
  protected void connect(String file) {
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
