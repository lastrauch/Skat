package logic;

import gui.GuiController;
import interfaces.LogicData;
import interfaces.LogicGui;
import interfaces.LogicNetwork;
import javafx.application.Application;
import javafx.stage.Stage;

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

  public GameController() {
    
  }
  
  public void control() {
  }

  public void setGameMode(GameMode gameMode) {
    this.gameMode = gameMode;
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
    System.out.println(" hKLO");
    javafx.application.Application.launch(GuiController.class, args);

  }

//  /* (non-Javadoc)
//   * @see javafx.application.Application#start(javafx.stage.Stage)
//   */
//  @Override
//  public void start(Stage arg0) throws Exception {
//    // TODO Auto-generated method stub
//    
//  }
}
