package gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import logic.Player;

public class AccountSettingsController implements Initializable{

  private GuiController main;
  private  GuiData interfGD = new ImplementsGuiInterface();
  private String username = null;
  private Image img = null;
  
  @FXML
  private JFXTextField newName;
  @FXML
  private JFXButton newPP;
  @FXML
  private ImageView pp;

  public AccountSettingsController() {
    this.main = new GuiController();
  }

  @FXML
  public void back() {
    main.displayChooseGame();
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    // TODO Auto-generated method stub
    System.out.println(LoginController.interfGL.getPlayer());
    if(LoginController.interfGL.getPlayer().getImage() != null) {
      pp.setImage(LoginController.interfGL.getPlayer().getImage());
    }
  }
  
  @FXML
  public void submit() {
    username = newName.getText();
    System.out.println("tipped username: "+ username);
    if(username != null) {
//      interfGD.changeName(username, LoginController.interfGL.getPlayer());
      System.out.println("acPlayer: "+LoginController.interfGL.getPlayer().getName());
      System.out.println("new name: "+interfGD.getPlayer(LoginController.interfGL.getPlayer()).getName());
    } 
    if(img != null) {
      interfGD.changeImage(LoginController.interfGL.getPlayer(), img);
    }
  }
  
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
      img = SwingFXUtils.toFXImage(bufferedImage, null);
      pp.setImage(img);
    } catch (IOException ex) {
      Logger.getLogger(CreateNewAccountController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  
}
