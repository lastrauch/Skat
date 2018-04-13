package gui;

import javafx.fxml.FXML;

public class HelpController {

  private GuiController main;

  public HelpController() {
    this.main = new GuiController();
  }

  @FXML
  public void overview() {
    main.displayHelp_Overview();

  }

  @FXML
  public void cards() {
    main.displayHelp_Cards();

  }

  @FXML
  public void deal() {
    main.displayHelp_Deal();
  }

  @FXML
  public void auction() {
    main.displayHelp_Auction();
  }

  @FXML
  public void posCon() {
    main.displayHelp_PossibleContracts();
  }

  @FXML
  public void play() {
    main.displayHelp_Play();
  }

  @FXML
  public void calVal() {
    main.displayHelp_CalculateValue();
  }

  @FXML
  public void scoring() {
    main.displayHelp_Scoring();
  }

  @FXML
  public void variations() {
    main.displayHelp_Variations();
  }

  @FXML
  public void back() {
    main.displayChooseGame();
  }

}
