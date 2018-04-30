package gui;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;

public class HelpPlayController {

  /**
   * @author lstrauch
   */
  private GuiController main;

  public HelpPlayController() {
    this.main = new GuiController();
  }

  /**
   * @author lstrauch
   */
  @FXML
  public void back() {
    main.displayHelp();
  }

}
