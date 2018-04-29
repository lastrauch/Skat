package database;


import java.awt.Image;
import java.io.File;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.ImageView;
import logic.Player;

public class Main extends ImplementsGuiInterface{
  public static ImplementsGuiInterface database;

  public static void main(String[] args) {
    
    
    // TODO Auto-generated method stub

    Image imae = new ImageIcon("grey.jpg").getImage();
    Player a = new Player("bb");
    database.insertPlayer(a);
    //database.changeImage(a,imae);




  }

}
