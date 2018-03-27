package network.messages;

import logic.*;

public class CardPlayed extends Message{
  private static final long serialVersionUID = 1L;
  private Player player;
  private Card card;
    
  public CardPlayed(Player player, Card card){
    super(MessageType.CARD_PLAYED);
    this.player = player;
    this.card = card;
  }
    
  public void setPlayer(Player player){
    this.player = player;
  }
    
  public Player getPlayer(Player player){
    return this.player;
  }
    
  public void setCard(Card card){
    this.card = card;
  }
    
  public Card getCard(){
    return this.card;
  }
}
