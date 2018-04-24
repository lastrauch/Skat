package gui;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class StartController {

  public GuiController main;
  @FXML
  private TextField nutzername;
  @FXML
  private Button neuanmelden;
  @FXML
  private AnchorPane root;
  @FXML
  private HBox hbox;
  @FXML
  private ImageView profilbild;

  @FXML
  public void login() {
    // Nutzernamen an Logik weitergeben
    // Spielerprofil laden
    Stage newStage = new Stage();
    try {
      root = (AnchorPane) FXMLLoader.load(getClass().getResource("Neuanmelden.fxml"));
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    // Image ima = new
    // Image(getClass().getResource("/SKAT_4/resources/Jclubs.jpg").toExternalForm());
    // profilbild.setImage(ima);

    BufferedImage im;
    try {
      im = ImageIO.read(getClass().getResource("/SKAT_4/resources/Jclubs.jpg"));
      profilbild.setImage(SwingFXUtils.toFXImage(im, null));
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    Scene scene = new Scene(root);
    newStage.setScene(scene);
    newStage.setTitle("Neuanmelden");
    newStage.showAndWait();
    System.exit(0);
  }

  @FXML
  public void neuerAccount() {
    // Settingsfeld für neuen Benutzernamen anzeigen
    nutzername.setPromptText("Neuer Nutzername");
    nutzername.setPrefHeight(71);
    hbox.getChildren().remove(neuanmelden);
  }

  public void setMain(GuiController main2) {
    // TODO Auto-generated method stub
    this.main = main2;

  }
}
