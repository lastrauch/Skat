package network;

import logic.Player;
import network.server.Server;

public class LogicReciever implements interfaces.LogicNetwork{

  public void hostGame(Player player) {
    Controller.hostGame(player);
  }

  public void joinLobby(Server server, Player player) {
    Controller.joinLobby(server, player);
  }

  public void addBot() {
    // TODO Auto-generated method stub
    //Erstelle Client für Bot
    //Sende an alle, dass neuer Client dabei
  }

  public void ping() {
    // TODO Auto-generated method stub
    
  }

  public void sendMsg() {
    // TODO Auto-generated method stub
    //Sende Nachricht an alle
  }

  public void sendGameSettings() {
    // TODO Auto-generated method stub
    
  }

  public void startGame() {
    // TODO Auto-generated method stub
    
  }

  public void dealCards() {
    // TODO Auto-generated method stub
    
  }

  public void yourTurn() {
    // TODO Auto-generated method stub
    
  }

  public void bet() {
    // TODO Auto-generated method stub
    
  }

  public void sendPlaySettings() {
    // TODO Auto-generated method stub
    
  }

  public void sendCardPlayed() {
    // TODO Auto-generated method stub
    
  }

  public void exitGame() {
    // TODO Auto-generated method stub
    
  }

}
