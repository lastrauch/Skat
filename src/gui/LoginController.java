package gui;

import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class LoginController implements Initializable {

  private GuiController main;
  private String username;

  @FXML
  JFXTextField textField;

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
  }

  @FXML
  public String login() {
    username = textField.getPromptText();
    // main.displayChooseGame();

    return username;
  }

}
