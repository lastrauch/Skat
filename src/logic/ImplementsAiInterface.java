package logic;

import interfaces.AILogic;
import interfaces.LogicGui;

public class ImplementsAiInterface extends GameController implements AILogic{

  //DIESE KLASSE IST VERMUTLICH UNNOETIG !!!!!
  public ImplementsAiInterface(LogicGui logicGui) {
    super(logicGui);
    // TODO Auto-generated constructor stub
  }

  @Override
  public Player copyPlayer(Player player) {
    // TODO Auto-generated method stub
    return null;
  }

}
