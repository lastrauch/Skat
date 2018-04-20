package gui;

import interfaces.LogicGui;
import logic.GameSettings;

public class ImplementsLogicGui extends GameSettingsController implements LogicGui {

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
    guiCon.displayGameSettings();
    gs.setCountRule(super.getCountRule());
    gs.setEnableKontra(super.getKontra());
    gs.setLimitedTime(super.getEnabledTime());
    if (super.getEnabledTime()) {
      gs.setTimeLimit(super.setLimitedTime());
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
    guiCon.displayChooseGame();

  }


}
