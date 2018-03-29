package network.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class Server extends Thread{
  private String serverName;
  private ServerSocket serverSocket;
  private int port;
  private List<ClientConnection> clientConnections;
  
  // TODO
  public Server(String serverName, int port){
    this.serverName = serverName;
    this.port = port;
    this.clientConnections = new ArrayList();
    
    try {
      this.serverSocket = new ServerSocket(port);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  
  
  
}
