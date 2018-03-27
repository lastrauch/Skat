package network.server;

import java.net.ServerSocket;
import java.util.List;

public class Server extends Thread{
  private String serverName;
  private ServerSocket serverSocket;
  private int port;
  private List<ClientConnection> clientConnections;
  
  
}
