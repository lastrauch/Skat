package network.messages;

import logic.Player;

public class Ping_Msg extends Message{
    private static final long serialVersionUID = 1L;
    private Player player;

    public Ping_Msg(Player player) {
        super(MessageType.PING);
        this.player = player;
    }
    
    public Player getPlayer(){
      return this.player;
    }
    
    public String toString(){
      return this.player.getName() + "pinged.";
    }
}
