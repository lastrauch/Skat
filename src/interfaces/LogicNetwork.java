package interfaces;

//Logic to Network, implemented by Network
public interface LogicNetwork {
  //Host game
  void hostGame();
  //Join Lobby
  void joinLobby();
  //Add bot
  void addBot();
  
  //Nachricht senden
    //Connect to server
    //Ping, keep Connection
  void ping();
    //Chat
  void sendMsg();
    //Settings senden
  void sendGameSettings();
    //Spiel start
  void startGame();
    //Karten dealen
  void dealCards();
    //Your Turn (eigentlich nur am Anfang wichtig)
  void yourTurn();
    //Bet
  void bet();
    //Play Settings
  void sendPlaySettings();
    //Card played
  void sendCardPlayed();
    //Exit Game
  void exitGame();
}
