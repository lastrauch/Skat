package network.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import logic.Player;
import network.messages.Message;
import network.messages.MessageType;
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
  
  public void run(){
      try{
          Message message;
          boolean connected = true;
          while(connected && (message = (Message) input.readObject()) != null){
              receiveMessage(message);
              this.server.getServerProtocol().writeToProtocol(message);
          }
      }catch(ClassNotFoundException e){
          e.printStackTrace();
      }catch(IOException e){
          e.printStackTrace();
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
  
  private void disconnect(){
    try{
        this.output.writeObject(new ClientDisconnect_Msg(this.owner));
        this.output.close();
        this.input.close();
        this.socket.close();
     }catch(IOException e){
         e.printStackTrace();
     }
  }
  
  private void sendMessage(Message message){
	  try{
		  this.output.writeObject(message);
	  }catch(IOException e){
		  e.printStackTrace();
	  }
  }
  
  private void receiveMessage(Message message){
	  switch(message.getType()){
	  	case YOUR_TURN :
	  	case CARD_PLAYED :
	  	case BET :
	  	case CHAT_MESSAGE :
	  	case GAME_SETTINGS :
	  	case PLAY_SETTINGS :
	  	case DEALT_CARDS :
	  	case CONNECTION_ANSWER :
	  	default :
	  		break;
	  }
  }
}
