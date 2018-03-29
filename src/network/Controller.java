package network;

import logic.Player;
import network.client.Client;
import network.server.Server;

public class Controller {
  private static Server server;
  private static Client client;
  
  
  
  public static void hostGame(Player player){
    //Create Server
    server = new Server("Server von " + player.getName(), 5050);
    server.run();
    //Joine der eigenen Lobby
    joinLobby(server, player);
  }
  
  public static void joinLobby(Server server, Player player) {
    // TODO Auto-generated method stub
    //Request Connection
    //Create Client
    //Forder GameSettings und andere Spieler an
    //Sende an alle, dass neuer Client dabei
    //Fordere letzten Chat an?
  }
}
