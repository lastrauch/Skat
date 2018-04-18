package logic;

import gui.GuiController;
import gui.ImplementsLogicGui;

public class GameController {

  private ImplementsLogicGui implementsLogicGui;
  private GameMode gameMode;
  private Player[] group;
  private GuiController guiController;
  
  public GameController(ImplementsLogicGui implementsLogicGui, GuiController guiController){
    this.implementsLogicGui = implementsLogicGui;
    this.group = new Player[4]; // if only 3 are playing group[3] is empty
    this.guiController = guiController;
  }
  
  public void control() {
  //  this.implementsLogicGui.openLoginScreen();
    this.implementsLogicGui.startGui();
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
    ImplementsLogicGui implementsLogicGui = new ImplementsLogicGui();
    GuiController guiController = new GuiController();
    GuiController.launch(args);
    guiController = implementsLogicGui.getGuiController();
    GameController gameController = new GameController(implementsLogicGui, guiController);
    
    
  }
}
