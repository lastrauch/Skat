package network.messages;

import logic.Player;

/**
 * Message that passed player disconnected.
 * 
 * @author fkleinoe
 */
public class ClientDisconnectMsg extends Message {
  private static final long serialVersionUID = 1L;
  private Player player;

  /**
   * Constructor.
   * 
   * @author fkleinoe
   * @param player that disconnected
   */
  public ClientDisconnectMsg(Player player) {
    super(MessageType.CLIENT_DISCONNECT);
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
