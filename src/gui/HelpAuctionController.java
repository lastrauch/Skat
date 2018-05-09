package gui;

import javafx.fxml.FXML;

public class HelpAuctionController {


  /**
   * initializes non-FXML attributes.
   * @author lstrauch
   */
  private GuiController main;

  /**
   * Constructor.
   *@author lstrauch
   */
  public HelpAuctionController() {
    this.main = new GuiController();
  }

  /**
   * go back to Help-screen.
   * @author lstrauch
   */
  @FXML
  public void back() {
    main.displayHelp();
  }

}
