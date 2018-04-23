package interfaces;

import java.util.List;

import logic.Card;
import logic.GameSettings;
import logic.PlayState;
import logic.Player;
import network.server.Server;

//Logic to Network, implemented by Network
public interface LogicNetwork {	
	//Host game
	void hostGame(Player player, GameSettings gs);
  
	//Get Server
	List<Server> getServer();
  
	//Join Lobby, sendet Nachricht zurück, ob es geklappt hat (Infos über die Lobby werden seperat empfangen)
	boolean joinLobby(Server server, Player player);
  
	//Chat
	void sendChatMessage(String message);
  
	//Settings senden
	void sendGameSettings(GameSettings gs);
  
	//Spiel start
	void startGame();
  
	//Karten dealen
	void dealCards(Player player, Card[] cards);
  
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
}
