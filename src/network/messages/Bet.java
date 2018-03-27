package network.messages;

import logic.Player;

public class Bet extends Message{
  private static final long serialVersionUID = 1L;
  private Player player;
  private int bet;
  
  public Bet(Player player, int bet){
    super(MessageType.BET);
    this.player = player;
    this.bet = bet;
  }
  
  public void setPlayer(Player player){
    this.player = player;
  }
  
  public Player getPlayer(){
    return this.player;
  }
  
  public void setBet(int bet){
    this.bet = bet;
  }
  
  public int getBet(){
    return this.bet;
  }
}
