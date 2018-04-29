package gui;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;

public class HelpOverviewController {

  @FXML
  private JFXButton back;

  /**
   * @author lstrauch
   */
  private GuiController main;

  /**
   *@author lstrauch
   */
  public HelpOverviewController() {
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
