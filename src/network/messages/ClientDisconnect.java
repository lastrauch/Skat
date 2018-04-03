package network.messages;

import logic.Player;

public class ClientDisconnect extends Message{
	private static final long serialVersionUID = 1L;
	private Player player;
	
	
	public ClientDisconnect(Player player) {
		super(MessageType.CLIENT_DISCONNECT);
		this.player = player;
	}
	
	public Player getPlayer(){
	  return this.player;
	}
	
	public String toString(){
	  return player.getName() + " disconnected.";
	}
}
