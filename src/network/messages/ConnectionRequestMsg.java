package network.messages;

import logic.Player;

public class ConnectionRequestMsg extends Message {
  private static final long serialVersionUID = 1L;
  private Player player;

  public ConnectionRequestMsg(Player player) {
    super(MessageType.CONNECTION_REQUEST);
    this.player = player;
  }

  public Player getPlayer() {
    return this.player;
  }
}
