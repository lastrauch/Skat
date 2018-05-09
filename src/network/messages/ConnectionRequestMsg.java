package network.messages;

import logic.Player;

/**
 * Passed player asks to join lobby on server.
 * 
 * @author fkleinoe
 */
public class ConnectionRequestMsg extends Message {
  private static final long serialVersionUID = 1L;
  private Player player;

  /**
   * Constructor.
   * 
   * @author fkleinoe
   * @param player that wants to join
   */
  public ConnectionRequestMsg(Player player) {
    super(MessageType.CONNECTION_REQUEST);
    this.player = player;
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
}
