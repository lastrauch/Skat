package gui;

import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXTextField;
import database.ImplementsGuiInterface;
import interfaces.GuiData;
import interfaces.GuiLogic;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import logic.GameController;

public class LoginController implements Initializable {

  private GuiController main;
  protected String username;
  protected ImplementsLogicGui implLG = new ImplementsLogicGui();
  protected static GameController gameCon;
  
  
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
  public void login() {
    username = textField.getText();
    main.displayChooseGame();
    gameCon = new GameController(implLG);
    GuiLogic interfaceL= gameCon;
//    interfaceL.login(username, null);
  }
  
  public void setImages() {
    GuiData inte = new ImplementsGuiInterface();
    jclubs.setImage(inte.getImage("clubs", "jack"));
    jspades.setImage(inte.getImage("spades", "jack"));
    jhearts.setImage(inte.getImage("hearts", "jack"));
    jdiamonds.setImage(inte.getImage("diamonds", "jack"));
    
  }
  
  public String getUsername() {
    return username;
  }

}
