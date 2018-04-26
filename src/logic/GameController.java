package logic;

import java.util.Iterator;
import java.util.List;
import ai.BotDifficulty;
import gui.ImplementsLogicGui;
import interfaces.GuiLogic;
import interfaces.LogicGui;
import interfaces.LogicNetwork;
import javafx.scene.image.Image;

public class GameController implements GuiLogic{

  private List<Player> group;
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

    Iterator<Player> it = this.group.iterator();
    
    while(it.hasNext()) {
      randomIndex = (int) (Math.random() * (this.group.size()));
      temp = this.group.get(index);
      this.group.set(index, this.group.get(randomIndex));
      this.group.set(randomIndex, temp);
      index ++;
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
    if (m == GameMode.MULTIPLAYER) {
      this.logicGui.openMultiPlayerLobby();
    }else {
      this.logicGui.openSinglePlayerLobby();
    }
  }

  @Override
  public void login(String username, Image profilepicture) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void deleteBot(String botname) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setBot(String botname, BotDifficulty difficulty) {
    // TODO Auto-generated method stub
    
  }


}
