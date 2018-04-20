package logic;

import gui.GuiController;
import interfaces.GuiLogic;
import interfaces.LogicData;
import interfaces.LogicGui;
import interfaces.LogicNetwork;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GameController {

  private Player[] group;
  private GuiController guiController;
  private LogicGui logicGui;
  private LogicData logicData;
  private LogicNetwork logicNetwork;
  private Game game; 
  
  public GameController(GuiController guiController) {
    this.group = new Player[4]; // if only 3 are playing group[3] is empty
    this.guiController = guiController;
  }

  public GameController() {
    
  }
  
  public void generateGame(GameMode gameMode) {
    this.game = new Game(gameMode);
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
    Application.launch(GuiController.class, args);
  }


}
