package gui;

import javafx.fxml.FXML;

public class HelpScoringController {

  /**
   * @author lstrauch
   */
  private GuiController main;

  /**
   *@author lstrauch
   */
  public HelpScoringController() {
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
