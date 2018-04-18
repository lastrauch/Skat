package gui;

import javafx.fxml.FXML;
import logic.GameMode;

public class ChooseGameController {

  @FXML
  private GuiController main;

  private GameMode gamemode;

  public ChooseGameController() {
    this.main = new GuiController();
  }

  @FXML
  public void SinglePlayer() {
    main.displayLobbyLocal();
    gamemode = GameMode.SINGLEPLAYER;
  }

  @FXML
  public void MultiPlayer() {
    main.displayLobbyOnline();
    gamemode = GameMode.MULTIPLAYER;
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

  public GameMode getGameMode() {
    return gamemode;
  }

}
