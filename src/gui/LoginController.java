package gui;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

public class LoginController {

  private GuiController main;
  private String username;

  @FXML
  JFXTextField textField;

  public LoginController() {
    this.main = new GuiController();
  }

  @FXML
  public String login() {
    username = textField.getPromptText();

    return username;
  }

  @FXML
  public void neuerAccount() {
    // Settingsfeld für neuen Benutzernamen anzeigen
    main.displayCreateNewAccount();
  }

}
