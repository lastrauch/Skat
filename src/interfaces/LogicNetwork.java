package interfaces;

import java.util.ArrayList;
import logic.Card;
import logic.Player;

//Logic to Network, implemented by Network
public interface LogicNetwork {

  /**
   * creates a new skat server
   */
  public void createServer();
  
  /**
   * sends a played card to all clients
   */
  public void sendCard(Card card);
  
  /**
   * sends the hand of a player (all his/her cards) to him/her
   * @param hand
   */
  public void sendHand(ArrayList<Card> hand, Player player);
  
  /**
   * sends a bet to the player 
   * @param bet
   * @param player
   */
  public void askforBet(int bet, Player player);
  
  /**
   * sends a player (that has to be updated) to all clients 
   * @param player
   */
  public void sendPlayer(Player player);
  
  /**
   * starts the game on all clients
   */
  public void startGame();
}
