package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database {
  Properties properties;
  protected Connection connection = null;
  private static String sep = System.getProperty("file.separator");
  private String dbname = "SkatData.db";
  protected String basedir = System.getProperty("user.dir") +  sep + "resources" + sep;
  private String filename = basedir + dbname; // default database file
  
  public boolean isConnected(){
      return (connection != null);
  }
  
  public void connect(){
      init();
      try {
          Class.forName("org.sqlite.JDBC");
          connection = DriverManager.getConnection("jdbc:sqlite:" + filename, properties);
          connection.setAutoCommit(false);
      } catch ( Exception e ) {
          System.out.println(e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
      }
      System.out.println("Opened database successfully");
  }
  public void disconnect(){
    if (connection != null){
        try {
            connection.commit();
            connection.close();
            connection = null;
        } catch(SQLException e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage() );
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println("Closed database successfully");
    }
  }
    private void init(){
      properties = new Properties();
      properties.setProperty("PRAGMA foreign_keys", "ON");
  }
}