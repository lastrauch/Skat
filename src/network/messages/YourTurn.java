package network.messages;

import logic.Player;

public class YourTurn extends Message{
    private static final long serialVersionUID = 1L;
    private Player player;

    public YourTurn(Player player){
      super(MessageType.YOUR_TURN);
      this.player = player;
    }
    
    public Player getPlayer(){
      return this.player;
    }
    
    public String toString(){
      return "It's " + this.player.getName() + "'s turn";
    }
}
