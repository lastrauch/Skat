package gui;

import javafx.fxml.FXML;

public class HelpVariationsController {

  /**
   * initializes non-FXML attributes.
   * @author lstrauch
   */
  private GuiController main;

  /**
   * Constructor.
   *@author lstrauch
   */
  public HelpVariationsController() {
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
