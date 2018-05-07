package network.test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Server2 extends Thread {
  private String serverName;
  private String ip;
  private ServerSocket serverSocket;
  private int port;
  private boolean serverRunning = false;

  public Server2(String serverName, int port) {
    this.serverName = serverName;
    this.port = port;
    try {
      InetAddress inetAddress = InetAddress.getLocalHost();
      this.ip = inetAddress.getHostAddress();
    } catch (UnknownHostException e1) {
      e1.printStackTrace();
    }

    try {
      this.serverSocket = new ServerSocket(port);
    } catch (IOException e) {
      e.printStackTrace();
    }
    Thread serverFinderThread = new Thread(ServerFinderThread2.getInstance());
    serverFinderThread.start();
  }

  public void run() {
    this.serverRunning = true;
    System.out.println("Server is running");


  }

  public void stopServer() {
    try {
      if (!this.serverSocket.isClosed()) {
        this.serverSocket.close();
      }
      this.serverRunning = false;
    } catch (SocketException e1) {
    } catch (IOException e2) {
      e2.printStackTrace();
    }
  }


  public String getServerName() {
	  return this.serverName;
  }
  
  public String getIP() {
	  return this.ip;
  }
}
