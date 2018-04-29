package gui;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;

public class HelpCardsController {


  /**
   * @author lstrauch
   */
  private GuiController main;

  /**
   *@author lstrauch
   */
  public HelpCardsController() {
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
