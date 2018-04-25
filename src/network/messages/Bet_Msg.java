package network.messages;

import logic.Player;

public class Bet_Msg extends Message{
  private static final long serialVersionUID = 1L;
  private Player player;
  private int bet;
  
  public Bet_Msg(Player player, int bet){
    super(MessageType.BET);
    this.player = player;
    this.bet = bet;
  }
  
  public Player getPlayer(){
	  return this.player;
  }
  
  public int getBet(){
    return this.bet;
  }
  
  public String toString(){
    return this.player.getName() + " bet: " + this.bet + ".";
  }
}
