package database;


import javafx.scene.image.Image;
import logic.Player;

public class Main extends ImplementsGuiInterface {
  public static ImplementsGuiInterface database;

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    database = new ImplementsGuiInterface();
    Player a = new Player("lalelu");
    database.insertPlayer(a);
    Image img = new Image("C:/duygupervane/grey.jpg");

    database.changeImage(a, img);

    

  }

}
