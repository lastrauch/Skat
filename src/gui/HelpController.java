package gui;

import javafx.fxml.FXML;

public class HelpController {

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
  public HelpController() {
    this.main = new GuiController();
  }

  /**
   * main.display Overwiew-screen.
   * 
   * @author lstrauch
   */
  @FXML
  public void overview() {
    main.displayHelp_Overview();

  }

  /**
   * main.display TheCards-Screen.
   * 
   * @author lstrauch
   */
  @FXML
  public void cards() {
    main.displayHelp_Cards();

  }

  /**
   * main.display TheDeal.screen.
   * 
   * @author lstrauch
   */
  @FXML
  public void deal() {
    main.displayHelp_Deal();
  }

  /**
   * main.display TheAuctionScreen.
   * 
   * @author lstrauch
   */
  @FXML
  public void auction() {
    main.displayHelp_Auction();
  }

  /**
   * main.display ThePossibleContracts-screen.
   * 
   * @author lstrauch
   */
  @FXML
  public void posCon() {
    main.displayHelp_PossibleContracts();
  }

  /**
   * main.display ThePlay-screen.
   * 
   * @author lstrauch
   */
  @FXML
  public void play() {
    main.displayHelp_Play();
  }

  /**
   * main.display CalValue-screen.
   * 
   * @author lstrauch
   */
  @FXML
  public void calVal() {
    main.displayHelp_CalculateValue();
  }

  /**
   * main.display TheScoring-screen.
   * 
   * @author lstrauch
   */
  @FXML
  public void scoring() {
    main.displayHelp_Scoring();
  }

  /**
   * main.display Variations-screen.
   * 
   * @author lstrauch
   */
  @FXML
  public void variations() {
    main.displayHelp_Variations();
  }

  /**
   * goes back to previous screen.
   * 
   * @author lstrauch
   */
  @FXML
  public void back() {
    LoginController.displayPrev();
  }

}
