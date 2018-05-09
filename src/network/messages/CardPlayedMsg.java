package network.messages;

import logic.Card;
import logic.Player;

/**
 * Passed player played passed card.
 * 
 * @author fkleinoe
 */
public class CardPlayedMsg extends Message {
  private static final long serialVersionUID = 1L;
  private Player player;
  private Card card;

  /**
   * Constructor.
   * 
   * @author fkleinoe
   * @param player that played a card
   * @param card that the player played
   */
  public CardPlayedMsg(Player player, Card card) {
    super(MessageType.CARD_PLAYED);
    this.player = player;
    this.card = card;
  }

  /**
   * Get player.
   * 
   * @author fkleinoe
   * @return player to get
   */
  public Player getPlayer() {
    return this.player;
  }

  /**
   * Get card.
   * 
   * @author fkleinoe
   * @return Card to get
   */
  public Card getCard() {
    return this.card;
  }
  
}
