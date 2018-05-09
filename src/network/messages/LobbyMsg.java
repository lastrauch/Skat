package network.messages;

import java.util.List;
import logic.GameSettings;
import logic.Player;

/**
 * Message containing the current lobby information.
 * 
 * @author fkleinoe
 */
public class LobbyMsg extends Message {
  private static final long serialVersionUID = 1L;
  private Player[] player;
  private GameSettings gameSettings;

  /**
   * Constructor.
   * 
   * @author fkleinoe
   * @param player that are in the lobby
   * @param gameSettings of the game
   */
  public LobbyMsg(List<Player> player, GameSettings gameSettings) {
    super(MessageType.LOBBY);
    this.player = player.toArray(new Player[player.size()]);
    this.gameSettings = gameSettings;
  }

  /**
   * Get player.
   * 
   * @author fkleinoe
   * @return Player[] to get
   */
  public Player[] getPlayer() {
    return this.player;
  }

  /**
   * Get gameSettings.
   * 
   * @author fkleinoe
   * @return GameSettings to get
   */
  public GameSettings getGameSettings() {
    return this.gameSettings;
  }
}
