package database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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

    String path = "Skat.db";
    try {
      this.connection = DriverManager.getConnection("jdbc:sqlite:" + path);
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    System.out.println(new File(path).length());
    if (new File(path).length() == 0) {
      try {
        this.createDb();
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

  public void createDb() throws SQLException {
    Statement stmt = this.connection.createStatement();
    stmt.executeUpdate("PRAGMA foreign_keys=OFF;\r\n" + "BEGIN TRANSACTION;\r\n"
        + "CREATE TABLE `Player` (\r\n" + "    `name`  TEXT\r\n" + ");\r\n" + "COMMIT;\r\n" + "");
    stmt.close();
  }


//  /**
//   * Connects Database with Java.
//   * 
//   * @param file name and path of database
//   * @author dpervane
//   */
//  private void connect(String file) {
//    try {
//      Class.forName("org.sqlite.JDBC");
//      this.connection = DriverManager.getConnection("jdbc:sqlite:" + file);
//    } catch (ClassNotFoundException e) {
//      e.printStackTrace();
//    } catch (SQLException e) {
//      e.printStackTrace();
//    }
//  }
}


