package interfaces;

import java.util.ArrayList;
import logic.Card;
import logic.PlayState;
import logic.Player;
import logic.Trick;

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
   * sends a card to a player
   * @param card
   * @param player
   */
  public void sendCard(Card card, Player player);
  
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
  public void updatePlayer(Player player);
  
  /**
   * starts the game on all clients
   */
  public void startGame();
  
  /**
   * sends a player to the host/server
   * @param player
   */
  public void joinLobby(Player player);
  
  /**
   * sends the playstate to all clients
   * @param ps
   */
  public void sendPlayState(PlayState ps);
  
//  /**
//   * sends the current trick to all clients
//   * @param tricks
//   */
//  public void updateTrick(Trick tricks);
  
  /*
  //Host game
  void hostGame(Player player, GameSettings gs);

  //Get Server
  List<Server> getServer();

  //Join Lobby, sendet Nachricht zur�ck, ob es geklappt hat (Infos �ber die Lobby werden seperat empfangen)
  boolean joinLobby(Server server, Player player);

  //Chat
  void sendChatMessage(String message);

  //Settings senden
  void sendGameSettings(GameSettings gs);

  //Spiel start
  void startGame();

  //Karten dealen
  void dealCards(Player player, List<Card> cards);

  //Your Turn (eigentlich nur am Anfang wichtig)
  void yourTurn(Player player);

  //Bet
  void bet(int bet);

  //Play Settings
  void sendPlayState(PlayState ps);

  //Card played
  void sendCardPlayed(Card card);

  //Exit Game
  void exitGame();
  */
}
