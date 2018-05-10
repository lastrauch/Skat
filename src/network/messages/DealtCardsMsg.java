package network.messages;

import java.util.List;
import logic.Card;
import logic.PlayState;
import logic.Player;

/**
 * Message containing the cards that are dealt to the passed player.
 * 
 * @author fkleinoe
 */
public class DealtCardsMsg extends Message {
  private static final long serialVersionUID = 1L;
  private Player player;
  private Card[] cards;
  private PlayState playState;

  /**
   * Constructor.
   * 
   * @author fkleinoe
   * @param player to pass the cards to
   * @param cards that were dealt
   * @param playState as dummy
   */
  public DealtCardsMsg(Player player, List<Card> cards, PlayState playState) {
    super(MessageType.DEALT_CARDS);
    this.player = player;
    this.cards = cards.toArray(new Card[cards.size()]);
    this.playState = playState;
  }

  /**
   * Get player.
   * 
   * @author fkleinoe
   * @return Player to get
   */
  public Player getPlayer() {
    return this.player;
  }

  /**
   * Get cards.
   * 
   * @author fkleinoe
   * @return Cards[] to get
   */
  public Card[] getCards() {
    return this.cards;
  }

  /**
   * Get playState.
   * 
   * @author fkleinoe
   * @return PlayState to get
   */
  public PlayState getPlayState() {
    return this.playState;
  }

}
