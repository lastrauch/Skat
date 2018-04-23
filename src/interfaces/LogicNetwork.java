package interfaces;

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
	Server[] getServer();
  
	//Join Lobby, sendet Nachricht zurück, ob es geklappt hat
	boolean joinLobby(Server server, Player player);
  
	//Chat
	void sendChatMessage(String message);
  
	//Settings senden
	void sendGameSettings(GameSettings gs);
  
	//Spiel start
	void startGame();
  
	//Karten dealen
	void dealCards(Card[] cards);
  
	//Your Turn (eigentlich nur am Anfang wichtig)
	void yourTurn();
  
	//Bet
	void bet(int bet);
  
	//Play Settings
	void sendPlayState(PlayState ps);
  
	//Card played
	void sendCardPlayed(Card card);
  
	//Exit Game
	void exitGame();
}
