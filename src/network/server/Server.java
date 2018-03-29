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
  private boolean serverRunning;
  private boolean chatRunning;
  private boolean lobbyRunning;
  private boolean gameRunning;
  
  // TODO
  public Server(String serverName, int port){
    this.serverName = serverName;
    this.port = port;
    this.clientConnections = new ArrayList<ClientConnection>();
    this.serverRunning = false;
    this.chatRunning = false;
    this.lobbyRunning = false;
    this.gameRunning = false;
    
    try {
      this.serverSocket = new ServerSocket(port);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public void run(){
    this.serverRunning = true;
    this.chatRunning = true;
    this.lobbyRunning = true;
    
    while(this.serverRunning){
      
    }
  }
 
  
}
