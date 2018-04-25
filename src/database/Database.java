package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

  public class Database {
    protected Connection connection;
    Properties properties;
    
    public Database() {
      this.connect(System.getProperty("user.dir") + System.getProperty("file.separator") + "resources"
              + System.getProperty("file.separator") + "SkatData.db");
    }
    
    /**
     * Stellt die Verbindung mit der Datenbank her.
     * 
     * @param file
     *            Name und Pfad der Datenbank
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



