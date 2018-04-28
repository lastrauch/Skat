package interfaces;

import java.util.List;
import logic.Card;
import logic.GameSettings;
import logic.PlayState;
import logic.Player;
import network.server.Server;

// Logic to Network, implemented by Network
public interface LogicNetwork {
  // Host game, we wanted to show comments to the other players like "only good players" could you
  // add an attribute like comment?
  // can we also have the server as a return (to manage the join gaime of the bots) ??
  Server hostGame(Player player, GameSettings gs, String comment);

  // Get Server
  List<Server> getServer();

  //Join Lobby, sendet Nachricht zur�ck, ob es geklappt hat (Infos �ber die Lobby werden seperat empfangen)
  boolean joinLobby(Server server, Player player);

  // Chat
  void sendChatMessage(String message);

  // Settings senden
  void sendGameSettings(GameSettings gs);

  // Spiel start
  void startGame();

  // Karten dealen
  void dealCards(Player player, List<Card> cards, PlayState ps);
  
  //Your Turn (eigentlich nur am Anfang wichtig)
  void yourTurn(Player player);

  // Bet
  void bet(int bet);

  // Play Settings
  void sendPlayState(PlayState ps);

  // Card played
  void sendCardPlayed(Card card);

  // Exit Game
  void exitGame();
}

