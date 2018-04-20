package gui;

import interfaces.LogicGui;
import javafx.application.Application;
import logic.GameSettings;

public class ImplementsLogicGui implements LogicGui {

  private GuiController guiCon = new GuiController();


  @Override
  public void openSinglePlayerLobby() {
    // TODO Auto-generated method stub
    guiCon.displayLobbyLocal();

  }

  @Override
  public void openMultiPlayerLobby() {
    // TODO Auto-generated method stub
    guiCon.displayLobbyOnline();

  }

  @Override
  public void setGameSettings(GameSettings gs) {
    // TODO Auto-generated method stub
    GameSettingsController gsCon = new GameSettingsController();
    guiCon.displayGameSettings();
    gs.setCountRule(gsCon.getCountRule());
    gs.setEnableKontra(gsCon.getKontra());
    gs.setLimitedTime(gsCon.getEnabledTime());
    if (gsCon.getEnabledTime()) {
      gs.setTimeLimit(gsCon.setLimitedTime());
    }
  }

  @Override
  public GuiController getGuiController() {
    // TODO Auto-generated method stub
    return guiCon;
  }


  @Override
  public void openGameModeScreen() {
    // TODO Auto-generated method stub

  }

  public static void main(String[] args) {
    Application.launch(GuiController.class, args);
  }

}
