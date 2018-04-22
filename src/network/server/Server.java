package network.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class Server extends Thread{
  private String serverName;
  private ServerSocket serverSocket;
  private int port;
  private List<ClientConnection> clientConnections;
  private ServerProtocol serverProtocol;
  private boolean serverRunning;
  
  public Server(String serverName, int port){
    this.serverName = serverName;
    this.port = port;
    this.clientConnections = new ArrayList<ClientConnection>();
    this.serverProtocol = new ServerProtocol();
    this.serverRunning = false;
    
    try {
      this.serverSocket = new ServerSocket(port);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public void run(){
    this.serverRunning = true;
    
    while(this.serverRunning){
      try(Socket newSocket = this.serverSocket.accept()){
        ClientConnection newClientConnection = new ClientConnection(this, newSocket);
        this.clientConnections.add(newClientConnection);
      } catch (SocketException e) {
        this.serverProtocol.writeToProtocol("Error!: Socket closed");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
 
  public void stopServer(){
      try {
          if (!this.serverSocket.isClosed()){
            this.serverProtocol.writeToProtocol("stopping server......");
              this.serverSocket.close();  
          }
      } catch (SocketException  e1){
        this.serverProtocol.writeToProtocol("Server stopped");   
      }  catch (IOException e2){
          e2.printStackTrace();   
      }
  }
  
  public ServerProtocol getServerProtocol(){
    return this.serverProtocol;
  }
  
  public String getServerName(){
    return this.serverName;
  }
  
}
