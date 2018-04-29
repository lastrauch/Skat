package gui;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;

public class HelpDealController {


  /**
   * @author lstrauch
   */
  private GuiController main;

  /**
   *@author lstrauch
   */
  public HelpDealController() {
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
