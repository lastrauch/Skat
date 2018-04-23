package network.messages;

import logic.Player;

public class ChatMessage_Msg extends Message{
  private static final long serialVersionUID = 1L;
  private Player player;
  private String msg;
  
  public ChatMessage_Msg(Player player, String msg){
    super(MessageType.CHAT_MESSAGE);
    this.player = player;
    this.msg = msg;
  }
  
  public Player getPlayer(){
	  return this.player;
  }
  
  public String getMsg(){
    return this.msg;
  }
  
  public String toString(){
    return this.player.getName() + ": " + this.msg;
  }

}
