package network.messages;

import logic.Card;

public class DealtCards_Msg extends Message{
	private static final long serialVersionUID = 1L;
	private Card[] cards;
	
	public DealtCards_Msg(Card[] cards){
		super(MessageType.DEALT_CARDS);
		this.cards = cards;
	}
	
	public void setCards(Card[] cards){
		this.cards = cards;
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
		return "Dealt Cards: " + str;
	}
}
