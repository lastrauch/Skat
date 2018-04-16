package ai;

import logic.Player;

public abstract class AIPlayer extends Player {
  
  public AIPlayer(String name) {
    super(name);
    // TODO Auto-generated constructor stub
  }

  public final Boolean isAIPlayer() {
    return true;
  }
}
  
  


