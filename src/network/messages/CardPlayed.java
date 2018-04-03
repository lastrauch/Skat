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
    
  public Player getPlayer(Player player){
    return this.player;
  }
    
  public Card getCard(){
    return this.card;
  }
  
  // TODO card benötigt eine toString Methode
  public String toString(){
    return this.player.getName() + " played " + this.card.toString();
  }
}
