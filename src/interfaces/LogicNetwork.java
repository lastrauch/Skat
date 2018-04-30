package interfaces;

import java.util.List;
import logic.Card;
import logic.GameSettings;
import logic.PlayState;
import logic.Player;
import network.server.Server;

// Logic to Network, implemented by Network
public interface LogicNetwork {
  // Host game
  Server hostGame(Player player, GameSettings gs, String comment);

  // Get Server
  List<Server> getServer();

  //Join Lobby, sendet Nachricht zur�ck, ob es geklappt hat (Infos �ber die Lobby werden seperat empfangen)
  boolean joinLobby(Server server, Player player);

  // Chat
  void sendChatMessage(String message);
  
  // sends out that one of the opponents announced Kontra
  void sendKontra();
  
  //sends out that the declarer announced Rekontra
  void sendRekontra();

  // Settings senden
  void sendGameSettings(GameSettings gs);

  // Spiel start
  void startGame();

  // Karten dealen
  void dealCards(Player player, List<Card> cards, PlayState ps);
  
  //Your Turn (eigentlich nur am Anfang wichtig)
  void yourTurn(Player player);

  // Bet
  void bet(int bet, Player player);

  // Play Settings
  void sendPlayState(PlayState ps);

  // Card played
  void sendCardPlayed(Card card);

  // Exit Game
  void exitGame();
}

