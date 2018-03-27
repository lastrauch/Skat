package network.messages;

import logic.Player;

public class ChatMessage extends Message{
  private static final long serialVersionUID = 1L;
  private Player player;
  private String msg;
  
  public ChatMessage(Player player, String msg){
    super(MessageType.CHAT_MESSAGE);
    this.player = player;
    this.msg = msg;
  }
  
  public void setPlayer(Player player){
    this.player = player;
  }
  
  public Player getPlayer(){
    return this.player;
  }
  
  public void setMsg(String msg){
    this.msg = msg;
  }
  
  public String getMsg(){
    return this.msg;
  }

}
