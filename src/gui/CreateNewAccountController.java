package gui;

import java.awt.image.BufferedImage;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import database.ImplementsGuiInterface;
import interfaces.GuiData;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import logic.Player;

public class CreateNewAccountController {

  /**
   * initialize non-FXML attributes.
   * 
   * @author lstrauch
   */
  private String username;
  protected ImplementsLogicGui implLg;
  private GuiData interfGd = new ImplementsGuiInterface();
  private GuiController main;
  private Label userExists = new Label();
  private Image ppicture = null;

  /**
   * initialize FXML-attributes.
   * 
   * @author lstrauch
   */
  @FXML
  private JFXTextField newUsername;
  @FXML
  private JFXButton profilepicture;
  @FXML
  private AnchorPane pane;
  @FXML
  protected ImageView pp;


  /**
   * Constructor.
   * 
   * @author lstrauch
   */
  public CreateNewAccountController() {
    this.main = new GuiController();
    this.implLg = new ImplementsLogicGui();
  }

  /**
   * creates a new user with a username and profilepicture in database.
   * 
   * @author lstrauch
   */
  @FXML
  public void submit() {
    this.username = newUsername.getText();
    if (ppicture != null) {
      interfGd.insertPlayer(new Player(username, ppicture));
    } else {
      interfGd.insertPlayer(new Player(username, null));
    }
    main.displayChooseGame();
    LoginController.interfGL.login(username, ppicture);
  }

  /**
   * displays a label if username already exists.
   * 
   * @author lstrauch
   */
  public void displayUserExists() {
    userExists.setLayoutX(44);
    userExists.setLayoutY(135);
    userExists.setPrefHeight(44);
    userExists.setPrefWidth(621);
    userExists.setText("Username already exists!");
    userExists.setFont(Font.font("System", FontWeight.BOLD, 21));
    userExists.setStyle("-fx-background-color: white; -fx-text-fill: red");
    userExists.setAlignment(Pos.CENTER);

    pane.getChildren().add(userExists);
  }

  /**
   * method to make it possible to upload a picture.
   * 
   * @author lstrauch
   */
  @FXML
  public void uploadPicture() {
    FileChooser fileChooser = new FileChooser();

    // Set extension filter
    FileChooser.ExtensionFilter extFilterJpg =
        new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
    FileChooser.ExtensionFilter extFilterPng =
        new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
    fileChooser.getExtensionFilters().addAll(extFilterJpg, extFilterPng);

    // Show open file dialog
    File file = fileChooser.showOpenDialog(null);

    try {
      BufferedImage bufferedImage = ImageIO.read(file);
      ppicture = SwingFXUtils.toFXImage(bufferedImage, null);
      pp.setImage(ppicture);
    } catch (IOException ex) {
      Logger.getLogger(CreateNewAccountController.class.getName()).log(Level.SEVERE, null, ex);
    }


  }

}
