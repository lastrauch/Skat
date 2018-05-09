package gui;

import javafx.fxml.FXML;

public class HelpDealController {


  /**
   * initializes non-FXML attributes.
   * @author lstrauch
   */
  private GuiController main;

  /**
   * Constructor.
   *@author lstrauch
   */
  public HelpDealController() {
    this.main = new GuiController();
  }

  /**
   * main.display HelpScreen.
   * @author lstrauch
   */
  @FXML
  public void back() {
    main.displayHelp();
  }

}
