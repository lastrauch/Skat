package network.messages;

import logic.Player;

public class Ping extends Message{
    private static final long serialVersionUID = 1L;
    private Player player;

    public Ping(Player player) {
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
