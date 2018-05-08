package network.test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;

public class Server extends Thread{
  private String serverName;
  private String ip;
  private ServerSocket serverSocket;
  private int port;
  private boolean running = false;
  
  public Server(String servername, int port) {
    this.serverName = serverName;
    this.port = port;
    try {
      InetAddress inetAddress = InetAddress.getLocalHost();
      this.ip = inetAddress.getHostAddress();
    }catch (UnknownHostException e) {
      e.printStackTrace();
    }
    try {
      this.serverSocket = new ServerSocket(port);
    }catch (IOException e) {
      e.printStackTrace();
    }
    
    Thread serverFinderThread = new Thread(ServerThread.getInstance(this));
    serverFinderThread.start();
  }
  
  public int getPort() {
    return this.port;
  }
}
