package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class database {
	
protected Connection connection;

private 
  
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

    
//    private void loadImgFromDB() {
//      try {
//          Connection con = mds.getConnection();
//
//          Statement stmt = con.createStatement();
//          ResultSet rs = stmt.executeQuery("SELECT * FROM imgStore");
//          if (!rs.next()) {
//              return;
//          }
//          byte[] bytes = rs.getBytes(2);
//
//          BufferedImage img = ImageIO.read(new ByteArrayInputStream(bytes));
//          System.out.println(img);
//          JFrame frm = new JFrame();
//          frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//          frm.getContentPane().add(new JLabel(new ImageIcon(img)),
//                  BorderLayout.CENTER);
//          frm.pack();
//          frm.setVisible(true);
//
//          stmt.close();
//          rs.close();
//          con.close();
//
//      } catch (SQLException e) {
//          e.printStackTrace();
//      } catch (IOException e) {
//          e.printStackTrace();
//      }
//    }
//}



