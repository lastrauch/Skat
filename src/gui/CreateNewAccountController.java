package gui;

import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import database.ImplementsGuiInterface;
import interfaces.GuiData;
import interfaces.GuiLogic;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import logic.GameController;
import logic.Player;

public class CreateNewAccountController {

  /**
   * @author lstrauch
   */
  private String username;
  protected ImplementsLogicGui implLG;
  private GuiData interfGD = new ImplementsGuiInterface();
  private GuiController main;
  private Label userExists = new Label();
  private Image ppicture = null;
  
  /**
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
   *@author lstrauch
   */
  public CreateNewAccountController() {
    this.main = new GuiController();
    this.implLG = new ImplementsLogicGui();
  }

  /**
   * @author lstrauch
   */
  @FXML
  public void submit() {
    this.username = newUsername.getText();

    // Auf Duygus Änderungen warten
    // try {
    // if(!interfGD.checkIfPlayerNew(username)) {
    // displayUserExists();
    // } else {
    // main.displayChooseGame();
    // GameController gameCon = new GameController(implLG);
    // GuiLogic interfaceL = gameCon;
    // interfaceL.login(username, null);
    // }
    // } catch (SQLException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }

    if(ppicture != null) {
      interfGD.insertPlayer(new Player(username, ppicture));
    }else {
      interfGD.insertPlayer(new Player(username, null));
    }
    main.displayChooseGame();
    LoginController.interfGL.login(username, ppicture);
  }

  /**
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
   * @author lstrauch
   */
  @FXML
  public void uploadPicture() {
    FileChooser fileChooser = new FileChooser();

    // Set extension filter
    FileChooser.ExtensionFilter extFilterJPG =
        new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
    FileChooser.ExtensionFilter extFilterPNG =
        new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
    fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

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
