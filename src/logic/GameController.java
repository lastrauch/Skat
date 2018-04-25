package logic;

import java.util.Iterator;
import java.util.List;
import gui.ImplementsLogicGui;
import interfaces.GuiLogic;
import interfaces.LogicGui;
import interfaces.LogicNetwork;
import interfaces.NetworkLogic;
import javafx.scene.image.Image;

public class GameController implements GuiLogic{

  private Player[] group;
  private LogicGui logicGui;   // interface from logic to gui
  private LogicNetwork logicNetwork;    // interface from logic to network
  private GuiLogic guiLogic;    // interface from gui to logic
  private List<ClientLogic> clientLogic;
  private Game game;
  
  public GameController(ImplementsLogicGui logicGui) {
    this.logicGui = logicGui;
  }

  public GameController() {}

 
  /**
   * defines in which order players "sitting on a table" (random)
   * 
   * @author sandfisc
   */
  public void defineSeatingList(Player[] group) {
    int randomIndex;
    int index = 0;
    Player temp;

    for (int i = 0; i < group.length; i++) {
      randomIndex = (int) (Math.random() * (this.group.length));
      temp = this.group[i];
      this.group[i] = this.group[randomIndex];
      this.group[randomIndex] = this.group[i];
    }
  }


  /* (non-Javadoc)
   * @see interfaces.GuiLogic#updateAccount(java.lang.String, java.lang.String, javafx.scene.image.Image)
   */
  @Override
  public void updateAccount(String oldUsername, String newUsername, Image profilbild) {
    // TODO Auto-generated method stub
    
  }

  /* (non-Javadoc)
   * @see interfaces.GuiLogic#decideGameMode(logic.GameMode)
   */
  @Override
  public void decideGameMode(GameMode m) {
    // TODO Auto-generated method stub
//    if (m == GameMode.MULTIPLAYER) {
//      this.logicGui.openMultiPlayerLobby();
//    }else {
//      this.logicGui.openSinglePlayerLobby();
//    }
  }

  /* (non-Javadoc)
   * @see interfaces.GuiLogic#login(java.lang.String)
   */
  @Override
  public void login(String username) {
    // TODO Auto-generated method stub
    
  }


}
