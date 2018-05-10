package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseHandler extends Database {
  /**
   * This class includes all Statements for SQL Database.
   * 
   * @author dpervane
   */
  protected static PreparedStatement insertPlayer;
  protected static PreparedStatement selectPlayerName;
  protected static PreparedStatement selectCard;
  protected static PreparedStatement selectCardDarker;
  protected static PreparedStatement changeName;

  private Connection con = null;


  /**
   * Connects this class with Database.
   * 
   * @author dpervane
   */
  public DatabaseHandler() {

    super();
    this.con = super.connection;
    this.prepareStatements();
  }

  /**
   * SQL Statements, which stores or changes Data from Database.
   * 
   * @author dpervane
   */
  public void prepareStatements() {
    try {
      insertPlayer = con.prepareStatement(
          "INSERT INTO Player (name) " + "VALUES (?);");

      selectPlayerName =
          con.prepareStatement("SELECT * FROM Player WHERE (name LIKE ?) " + "ORDER BY name;");

      changeName = con.prepareStatement("UPDATE Player SET name = ? WHERE (name LIKE ?);");

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
