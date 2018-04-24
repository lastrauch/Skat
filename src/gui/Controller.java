package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Controller extends Application {

  private ImageView card1 = new ImageView();

  @Override
  public void start(Stage primaryStage) throws Exception {
    // TODO Auto-generated method stub
    FXMLLoader loader = new FXMLLoader(getClass().getResource("InGame.fxml"));

  }

}
