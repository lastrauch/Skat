package gui;

import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXTextField;
import database.ImplementsGuiInterface;
import interfaces.GuiData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

public class LoginController implements Initializable {

  private GuiController main;
  private String username;

  @FXML
  JFXTextField textField;
  @FXML ImageView jclubs, jspades, jhearts, jdiamonds;

  public LoginController() {
    this.main = new GuiController();
  }


  @FXML
  public void neuerAccount() {
    // Settingsfeld für neuen Benutzernamen anzeigen
    main.displayCreateNewAccount();
    
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // TODO Auto-generated method stub
    setImages();
  }

  @FXML
  public String login() {
    username = textField.getPromptText();
    // main.displayChooseGame();

    return username;
  }
  
  public void setImages() {
    GuiData inte = new ImplementsGuiInterface();
    jclubs.setImage(inte.getImage("clubs", "B"));
    jspades.setImage(inte.getImage("spades", "B"));
    jhearts.setImage(inte.getImage("hearts", "B"));
    jdiamonds.setImage(inte.getImage("diamonds", "B"));
    
  }

}
