package interfaces;

import logic.Player;

// AI to Logic, implemented by Logic
/**
 * This is an interface between the AI and the logic. Methods are called by the AI on the logic.
 * 
 * @author fkleinoe
 */
public interface AiLogic {

  /**
   * Get a deep copy of a player instance.
   * 
   * @author fkleinoe
   * @param player that should be copied
   * @return Player to get
   */
  public Player copyPlayer(Player player);

  /**
   * AI wants to announce kontra.
   * 
   * @author fkleinoe
   */
  public void announceKontra();

  /**
   * AI wants to announce rekontra.
   * 
   * @author fkleinoe
   */
  public void announceRekontra();
}
