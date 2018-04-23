package network.messages;

import logic.Card;
import logic.Player;

public class DealtCards_Msg extends Message{
	private static final long serialVersionUID = 1L;
	private Player player;
	private Card[] cards;
	
	public DealtCards_Msg(Player player, Card[] cards){
		super(MessageType.DEALT_CARDS);
		this.player = player;
		this.cards = cards;
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
	public Card[] getCards(){
		return this.cards;
	}
	
	// TODO logic.Card neets a toString Method
	public String toString(){
		String str = "";
		for(int i=0; i<this.cards.length; i++){
			str += this.cards[i].toString() + "\n";
		}
		return "Dealt Cards to " + this.player.getName() + ": " + str;
	}
}
