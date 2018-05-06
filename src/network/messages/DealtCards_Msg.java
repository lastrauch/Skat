package network.messages;

import java.util.List;
import logic.Card;
import logic.PlayState;
import logic.Player;

public class DealtCards_Msg extends Message {
  private static final long serialVersionUID = 1L;
  private Player player;
  private Card[] cards;
  private PlayState ps;

  public DealtCards_Msg(Player player, List<Card> cards, PlayState ps) {
    super(MessageType.DEALT_CARDS);
    this.player = player;
    this.cards = cards.toArray(new Card[cards.size()]);
    this.ps = ps;
  }

  public Player getPlayer() {
    return this.player;
  }

  public Card[] getCards() {
    return this.cards;
  }

  public PlayState getPlayState() {
    return this.ps;
  }

  // TODO logic.Card neets a toString Method
  public String toString() {
    String str = "";
    for (int i = 0; i < this.cards.length; i++) {
      str += this.cards[i].toString() + "\n";
    }
    return "Dealt Cards to " + this.player.getName() + ": " + str;
  }
}
