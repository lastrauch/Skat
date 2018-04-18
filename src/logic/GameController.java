package logic;

import gui.GuiController;
import interfaces.LogicData;
import interfaces.LogicGui;
import interfaces.LogicNetwork;

public class GameController {

  private GameMode gameMode;
  private Player[] group;
  private GuiController guiController;
  private LogicGui logicGui;
  private LogicData logicData;
  private LogicNetwork logicNetwork;

  public GameController(GuiController guiController) {
    this.group = new Player[4]; // if only 3 are playing group[3] is empty
    this.guiController = guiController;
  }

  public void control() {
    this.logicGui.logIn();
    this.askForGameMode();
  }

  /**
   * log in a new or "old" (#database) player
   * 
   * @author sandfisc
   */
  public void logIn() {
    // get the name the player has typed in
    String logInName = this.logicGui.logIn();
    
    if (this.logicData.checkIfPlayerNew(logInName)) {
      // if the player is new, generate a new player an save him/her in the group and in the database
      this.group[0] = new Player(logInName);
      this.logicData.insertPlayer(this.group[0]);
    } else {
      // if the player is not new, load him/her out of the database 
      this.group[0] = this.logicData.getPlayer(logInName);
    }
  }

  /**
   * let the player decide if he wants to play multplayer or singleplayer
   * 
   * @author sandfisc
   */
  public void askForGameMode() {
    this.gameMode = this.logicGui.decideGameMode();

    if (this.gameMode == GameMode.SINGLEPLAYER) {
      this.logicGui.openSinglePlayerLobby();
    } else if (this.gameMode == GameMode.MULTIPLAYER) {
      this.logicGui.openMultiPlayerLobby();
    }
  }
  
//  public void organizeSinglePlayerGame() {
//    // two new AIs depending on the difficulty the (real) player has chosen
//    
//  }
//  
//  public void organizeMultiPlayerGame() {
//    // search for lobby / create new one
//  
//  }

  public static void main(String[] args) {
    // // the whole program is supposed to be running here .. i don't think so
    // ImplementsLogicGui implementsLogicGui = new ImplementsLogicGui();
    // GuiController guiController = new GuiController();
    // GuiController.launch(args);
    // guiController = implementsLogicGui.getGuiController();
    // GameController gameController = new GameController(implementsLogicGui, guiController);
    //

  }
}
