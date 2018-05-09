package network.messages;

import logic.Player;

/**
 * Message that the player bet.
 * 
 * @author fkleinoe
 */
public class BetMsg extends Message {
  private static final long serialVersionUID = 1L;
  private Player player;
  private int bet;

  /**
   * Constructor.
   * 
   * @author fkleinoe
   * @param player that placed the bet
   * @param bet that was placed
   */
  public BetMsg(Player player, int bet) {
    super(MessageType.BET);
    this.player = player;
    this.bet = bet;
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
   * Get bet.
   * 
   * @author fkleinoe
   * @return int to get
   */
  public int getBet() {
    return this.bet;
  }

}
