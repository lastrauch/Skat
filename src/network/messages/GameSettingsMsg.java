package network.messages;

import logic.GameSettings;

/**
 * Message containing the GameSettings.
 * 
 * @author fkleinoe
 */
public class GameSettingsMsg extends Message {
  private static final long serialVersionUID = 1L;
  private GameSettings gameSettings;

  /**
   * Constructor.
   * 
   * @author fkleino
   * @param gameSettings to send
   */
  public GameSettingsMsg(GameSettings gameSettings) {
    super(MessageType.GAME_SETTINGS);
    this.gameSettings = gameSettings;
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
