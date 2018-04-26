package gui;

import com.jfoenix.controls.JFXButton;
import interfaces.GuiLogic;
import javafx.fxml.FXML;
import logic.GameController;
import logic.GameMode;

public class LobbyOnlineController {

  @FXML
  private JFXButton join;
  @FXML
  private GuiController main;

  public LobbyOnlineController() {
    this.main = new GuiController();
  }

  @FXML
  public void join() {
    GuiLogic interf = new GameController();
    interf.joinGame(main.getLoginCon().getUsername());
  }

  @FXML
  public void settings() {
    main.displaySettings();
  }

  @FXML
  public void help() {
    main.displayHelp();
  }

  @FXML
  public void accountSettings() {
    main.displayAccountSettings();
  }

  public void startNewGame() {
    main.displayGameSettings(GameMode.MULTIPLAYER);
  }

}
