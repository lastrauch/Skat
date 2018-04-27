package gui;

import interfaces.LogicGui;

public class ImplementsLogicGui extends GameSettingsController implements LogicGui {

  private GuiController guiCon = new GuiController();

  @Override
  public void startInGameScreen() {
    // TODO Auto-generated method stub
    guiCon.displayInGame();
    
  }



}
