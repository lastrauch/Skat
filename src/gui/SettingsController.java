package gui;

import javafx.fxml.FXML;

public class SettingsController {

  /**
   * @author lstrauch
   */
  private GuiController main;

  /**
   *@author lstrauch
   */
  public SettingsController() {
    this.main = new GuiController();
  }

  /**
   * @author lstrauch
   */
  @FXML
  public void back() {
    LoginController.displayPrev();
  }
}
