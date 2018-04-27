package gui;

import interfaces.LogicGui;
import logic.GameSettings;

public class ImplementsLogicGui extends GameSettingsController implements LogicGui {

  private GuiController guiCon = new GuiController();


  @Override
  public void setGameSettings(GameSettings gs) {
    // TODO Auto-generated method stub
  }

  @Override
  public GuiController getGuiController() {
    // TODO Auto-generated method stub
    return guiCon;
  }

  @Override
  public void startInGameScreen() {
    // TODO Auto-generated method stub
    guiCon.displayInGame();
    
  }



}
