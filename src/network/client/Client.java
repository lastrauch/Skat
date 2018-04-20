package network.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import logic.Player;
import network.messages.*;
import network.server.Server;

public class Client {
  private Server server;
  private int port;
  private Socket socket;
  private Player owner;
  private boolean isHost;
  private ObjectOutputStream output; //Ausgabe zum Server
  private ObjectInputStream input; //Eingabe vom Server
  
  public Client(Server server, Player player, Boolean isHost, int port){
    this.server = server;
    this.owner = player;
    this.isHost = isHost;
    this.port = port;
    
    boolean connectionEstablished = connect();
    if (!connectionEstablished){
      System.out.println("Sorry: No connection to " + server.getServerName() + ": " + port);
    }
  }
 
  // TODO ask for Connection
  private boolean connect(){
    try{
      socket = new Socket(server.getName(), port);
      output = new ObjectOutputStream(socket.getOutputStream());
      input = new ObjectInputStream(socket.getInputStream());
    } catch (UnknownHostException e){
      e.printStackTrace();
      return false;
    }catch(IOException e){
      e.printStackTrace();
      return false;
    }
    return true;
  }
  
  void endClient(){
    try{
        this.output.writeObject(new ClientDisconnect_Msg(this.owner));
        this.output.close();
        this.input.close();
        this.socket.close();
     }catch(IOException e){
         e.printStackTrace();
     }
  }
  
  // TODO Nachrichten senden
  public void sendChatMessageToServer(String msg){
    try {
      output.writeObject(new ChatMessage_Msg(owner, msg));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
