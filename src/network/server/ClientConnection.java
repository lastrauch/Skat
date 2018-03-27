package network.server;

import logic.Player;
import java.io.*;
import java.net.Socket;


public class ClientConnection extends Thread{
  private Server server;
  private Socket socket;
  private ObjectInputStream input;
  private ObjectOutputStream output;
  private boolean running;
  
  private int playerID;
  private Player player;
  
  public ClientConnection(Server server, Socket socket, Player player){
    this.server = server;
    this.player = player;
    
    try{
      this.socket = socket;
      this.output = new ObjectOutputStream(socket.getOutputStream());
      this.input = new ObjectInputStream(socket.getInputStream());
    }catch(IOException e){
      System.out.println("Connection error");
      e.printStackTrace();
    }
  }

}
