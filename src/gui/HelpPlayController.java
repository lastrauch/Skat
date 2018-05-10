package gui;

import javafx.fxml.FXML;

public class HelpPlayController {

  /**
   * initializes non-FXML attributes.
   * 
   * @author lstrauch
   */
  private GuiController main;

  /**
   * Constructor.
   * 
   * @author lstrauch
   */
  public HelpPlayController() {
    this.main = new GuiController();
  }

  /**
   * main.display Help-screen.
   * 
   * @author lstrauch
   */
  @FXML
  public void back() {
    main.displayHelp();
  }

}
