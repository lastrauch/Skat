package network.messages;

import java.util.List;
import logic.Card;
import logic.Player;

public class DealtCards_Msg extends Message{
	private static final long serialVersionUID = 1L;
	private Player player;
	private List<Card> cards;
	
	public DealtCards_Msg(Player player, List<Card> cards){
		super(MessageType.DEALT_CARDS);
		this.player = player;
		this.cards = cards;
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
	public List<Card> getCards(){
		return this.cards;
	}
	
	// TODO logic.Card neets a toString Method
	public String toString(){
		String str = "";
		for(int i=0; i<this.cards.size(); i++){
			str += this.cards.get(i).toString() + "\n";
		}
		return "Dealt Cards to " + this.player.getName() + ": " + str;
	}
}
