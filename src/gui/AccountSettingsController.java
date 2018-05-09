package gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

public class AccountSettingsController implements Initializable {


  /**
   * initialize non-FXML attributes.
   * @author lstrauch
   */
  private String username = null;
  private Image img = null;



  /**
   * initializes FXML-attributes.
   * @author lstrauch
   */
  @FXML
  private JFXTextField newName;
  @FXML
  private JFXButton newPP;
  @FXML
  private ImageView pp;


  /**
   * Constructor.
   * @author lstrauch
   */
  public AccountSettingsController() {}

  /**
   * Go back to the previous screen.
   * @author lstrauch
   */
  @FXML
  public void back() {
    LoginController.displayPrev();
  }

  /**
   * (non-Javadoc)
   * 
   * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
   * 
   * @author lstrauch
   **/
  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    // TODO Auto-generated method stub
    System.out.println(LoginController.interfGL.getPlayer());
    if (LoginController.interfGL.getPlayer().getImage() != null) {
      pp.setImage(LoginController.interfGL.getPlayer().getImage());
    }
  }


  /**
   * Updates the name and profile picture in database.
   * @author lstrauch
   */
  @FXML
  public void submit() {
    username = newName.getText();
    try {
      if (LoginController.interfGD.checkIfPlayerNew(username)) {
        if (username != null) {
          LoginController.interfGD.changeName(username, LoginController.interfGL.getPlayer());
          LoginController.displayPrev();
        }
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    // if(img != null) {
    // LoginController.interfGD.changeImage(LoginController.interfGL.getPlayer(), img);
    // }
  }


  /**
   * Open an external window to choose a *.jpg or *.png picture.
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
      img = SwingFXUtils.toFXImage(bufferedImage, null);
      pp.setImage(img);
    } catch (IOException ex) {
      Logger.getLogger(CreateNewAccountController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }



}
