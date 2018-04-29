package gui;

import javafx.fxml.FXML;

public class HelpController {

  /**
   * @author lstrauch
   */
  private GuiController main;

  /**
   *@author lstrauch
   */
  public HelpController() {
    this.main = new GuiController();
  }

  /**
   * @author lstrauch
   */
  @FXML
  public void overview() {
    main.displayHelp_Overview();

  }

  /**
   * @author lstrauch
   */
  @FXML
  public void cards() {
    main.displayHelp_Cards();

  }

  /**
   * @author lstrauch
   */
  @FXML
  public void deal() {
    main.displayHelp_Deal();
  }

  /**
   * @author lstrauch
   */
  @FXML
  public void auction() {
    main.displayHelp_Auction();
  }

  /**
   * @author lstrauch
   */
  @FXML
  public void posCon() {
    main.displayHelp_PossibleContracts();
  }

  /**
   * @author lstrauch
   */
  @FXML
  public void play() {
    main.displayHelp_Play();
  }

  /**
   * @author lstrauch
   */
  @FXML
  public void calVal() {
    main.displayHelp_CalculateValue();
  }

  /**
   * @author lstrauch
   */
  @FXML
  public void scoring() {
    main.displayHelp_Scoring();
  }

  /**
   * @author lstrauch
   */
  @FXML
  public void variations() {
    main.displayHelp_Variations();
  }

  /**
   * @author lstrauch
   */
  @FXML
  public void back() {
    main.displayChooseGame();
  }

}
