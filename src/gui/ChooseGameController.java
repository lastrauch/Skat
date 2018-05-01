package gui;

import interfaces.GuiLogic;
import javafx.fxml.FXML;
import logic.GameController;
import logic.GameMode;

public class ChooseGameController {

  /**
   * @author lstrauch
   */
  @FXML
  private GuiController main;

  /**
   * @author lstrauch
   */
  private GameMode gamemode;

  /**
   *@author lstrauch
   */
  public ChooseGameController() {
    this.main = new GuiController();
    GuiController.prevScreen = 1;
  }

  /**
   * @author lstrauch
   */
  @FXML
  public void SinglePlayer() {
    main.displayGameSettings(GameMode.SINGLEPLAYER);
  }

  /**
   * @author lstrauch
   */
  @FXML
  public void MultiPlayer() {
    main.displayLobbyOnline();

  }

  /**
   * @author lstrauch
   */
  @FXML
  public void settings() {
    main.displaySettings();
  }

  /**
   * @author lstrauch
   */
  @FXML
  public void help() {
    main.displayHelp();
  }

  /**
   * @author lstrauch
   */
  @FXML
  public void accountSettings() {
    main.displayAccountSettings();
  }

  /**
   * @author lstrauch
   * @return
   */
  public GameMode getGameMode() {
    return gamemode;
  }

}
