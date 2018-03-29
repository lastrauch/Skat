package network;

import network.client.Client;
import network.server.Server;

public class Controller {
  private static Server server;
  private Client client;

  
  
  public static void hostGame(){
    //Create Server
    server = new Server("Der Server", 5050);
    //Create Client
    
    
  }
}
