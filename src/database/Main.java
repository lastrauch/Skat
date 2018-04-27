package database;
import  database.DatabaseHandler;
import database.ImplementsGuiInterface;

public class Main {
  public static DatabaseHandler database;
  public static ImplementsGuiInterface i;

  public static void main(String[] args) {
    i = new ImplementsGuiInterface();
    i.connect();
    i.prepareStatements();
   
//    database = new DatabaseHandler();
//    database.connect();
//    database.prepareStatements();
//    database.disconnect();
    

  }

}
