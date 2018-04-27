package network.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import logic.GameSettings;
import logic.PlayState;
import logic.Player;

public class Server extends Thread{
  private String serverName;
  private ServerSocket serverSocket;
  private int port;
  private List<ClientConnection> clientConnections;
  private boolean serverRunning = false;
  
  private GameSettings gs;
  private PlayState ps;
  private List<Player> player;

  public Server(String serverName, int port, GameSettings gs){
    this.serverName = serverName;
    this.port = port;
    this.clientConnections = new ArrayList<ClientConnection>();
    
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
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
 
  public void stopServer(){
      try {
          if (!this.serverSocket.isClosed()){
              this.serverSocket.close();  
          }
          this.serverRunning = false;
      } catch (SocketException  e1){
      }  catch (IOException e2){
          e2.printStackTrace();   
      }
  }
  
  public int getPort(){
	  return this.port;
  }
  
  public String getServerName(){
    return this.serverName;
  }
  
  public int getNumberOfPlayers(){
	  return this.clientConnections.size();
  }
  
  public List<ClientConnection> getClientConnections(){
	  return this.clientConnections;
  }
  
  public void setGameSettings(GameSettings gs){
	  this.gs = gs;
  }
  
  public GameSettings getGameSettings(){
	  return this.gs;
  }
  
  public void setPlayState(PlayState ps){
	  this.ps = ps;
  }
  
  public PlayState getPlayState(){
	  return this.ps;
  }
  
  public List<Player> getPlayer(){
	  return this.player;
  }
  
  public void addPlayer(Player player){
	  this.player.add(player);
  }
  
  public void removePlayer(Player player){
	  this.player.remove(player);
  }
  
  public void removeClientConnection(ClientConnection connection){
	  this.clientConnections.remove(connection);
  }

}