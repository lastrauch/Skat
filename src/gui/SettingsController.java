package gui;

import javafx.fxml.FXML;

public class SettingsController {

  private GuiController main;

  public SettingsController() {
    this.main = new GuiController();
  }

  @FXML
  public void back() {
    main.displayChooseGame();
  }
}
