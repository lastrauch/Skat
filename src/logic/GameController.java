package logic;

import gui.ImplementsLogicGui;

public class GameController {

  private ImplementsLogicGui implementsLogicGui;
  private GameMode gameMode;
  
  public GameController(){
    this.implementsLogicGui = new ImplementsLogicGui();
  }
  
  public void askForGameMode() {
    this.gameMode = this.implementsLogicGui.decideGameMode();
    
    if (this.gameMode == GameMode.SINGLEPLAYER) {
      this.implementsLogicGui.openSinglePlayerLobby();
    }else if (this.gameMode == GameMode.MULTIPLAYER) {
      this.implementsLogicGui.openMultiPlayerLobby();
    }
  }
  
  public static void main(String[] args) {
    // the whole program is supposed to be running here
    
    
  }
}
