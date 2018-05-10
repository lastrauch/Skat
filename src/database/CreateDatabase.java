package database;

import java.sql.Statement;
import java.util.Properties;

public class CreateDatabase extends Database {
    Properties properties;
    private Statement stmt;
    
    public CreateDatabase() {
      super();
      this.createAllTables();
    }
    
    private void createAllTables() {
      this.createTableCards();
      this.createTableCardsDark();
      this.createTablePlayer();
    }

    private void createTableCards(){
        try {
            stmt = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS Cards " +
                    " colour           TEXT    NOT NULL, " + 
                    " number           TEXT    NOT NULL, " + 
                    " image            BLOB  )"; 
            stmt.executeUpdate(sql);
            stmt.close();
            connection.commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage() );
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println("Table Cards created successfully");
    }
    private void createTableCardsDark(){
      try {
          stmt = connection.createStatement();
          String sql = "CREATE TABLE IF NOT EXISTS CardsDark " +
                  " colour           TEXT    NOT NULL, " + 
                  " number           TEXT    NOT NULL, " + 
                  " image            BLOB  )"; 
          stmt.executeUpdate(sql);
          stmt.close();
          connection.commit();
      } catch (Exception e) {
          System.err.println(e.getClass().getName() + ": " + e.getMessage() );
          e.printStackTrace();
          System.exit(0);
      }
      System.out.println("Table CardsDark created successfully");
    }
    private void createTablePlayer(){
      try {
          stmt = connection.createStatement();
          String sql = "CREATE TABLE IF NOT EXISTS Player " +
                  "(id INTEGER PRIMARY  KEY  AUTOINCREMENT," +
                  " name              TEXT    NOT NULL)"; 
          stmt.executeUpdate(sql);
          stmt.close();
          connection.commit();
      } catch (Exception e) {
          System.err.println(e.getClass().getName() + ": " + e.getMessage() );
          e.printStackTrace();
          System.exit(0);
      }
      System.out.println("Table Player created successfully");
  }

    
  }


