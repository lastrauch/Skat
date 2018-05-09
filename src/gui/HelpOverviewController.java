package gui;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;

public class HelpOverviewController {

  @FXML
  private JFXButton back;

  /**
   * initializes non-FXML attributes.
   * @author lstrauch
   */
  private GuiController main;

  /**
   * Constructor.
   *@author lstrauch
   */
  public HelpOverviewController() {
    this.main = new GuiController();
  }

  /**
   * main.display Help-screen.
   * @author lstrauch
   */
  @FXML
  public void back() {
    main.displayHelp();
  }

}
