package gui;

import interfaces.LogicGui;

public class ImplementsLogicGui extends GameSettingsController implements LogicGui {

  /**
   * @author lstrauch
   */
  private GuiController guiCon = new GuiController();

  /** (non-Javadoc)
   * @see interfaces.LogicGui#startInGameScreen()
   * 
   * @author lstrauch
   */
  @Override
  public void startInGameScreen() {
    // TODO Auto-generated method stub
    guiCon.displayInGame();
    
  }



}
