package gui;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;

public class LobbyOnlineController {

  @FXML
  private JFXButton join;
  @FXML
  private GuiController main;

  public LobbyOnlineController() {
    this.main = new GuiController();
  }

  @FXML
  public void join() {
    main.displayInGame();
  }

  @FXML
  public void settings() {
    main.displaySettings();
  }

  @FXML
  public void help() {
    main.displayHelp();
  }

  @FXML
  public void accountSettings() {
    main.displayAccountSettings();
  }

}
