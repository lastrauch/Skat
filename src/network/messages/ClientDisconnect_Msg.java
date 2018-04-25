package network.messages;

import logic.Player;

public class ClientDisconnect_Msg extends Message{
	private static final long serialVersionUID = 1L;
	private Player player;
	
	public ClientDisconnect_Msg(Player player) {
		super(MessageType.CLIENT_DISCONNECT);
		this.player = player;
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
	public String toString(){
	  return this.player.getName() + " disconnected.";
	}
}
