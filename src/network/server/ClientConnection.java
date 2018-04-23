package network.server;

import logic.Player;
import network.messages.*;
import java.io.*;
import java.net.Socket;


public class ClientConnection extends Thread{
  private Server server;
  private Socket socket;
  private ObjectInputStream input;  //Eingabe vom Client
  private ObjectOutputStream output; //Ausgabe zum Client
  private boolean running;
  
  private int playerID;
  private Player player;
  
  public ClientConnection(Server server, Socket socket){
    this.server = server;
    try{
      this.socket = socket;
      this.output = new ObjectOutputStream(socket.getOutputStream());
      this.input = new ObjectInputStream(socket.getInputStream());
    }catch(IOException e){
      System.out.println("Connection error");
      e.printStackTrace();
    }
  }
  
  public ClientConnection(Server server, Socket socket, Player player){
    this.server = server;
    this.player = player;
    
    try{
      this.socket = socket;
      this.output = new ObjectOutputStream(socket.getOutputStream());
      this.input = new ObjectInputStream(socket.getInputStream());
    }catch(IOException e){
      e.printStackTrace();
    }
  }

    public void run(){
        try{
            Message message;
            boolean connected = true;
            while(connected && (message = (Message) input.readObject()) != null){
                receiveMessage(message);
            }
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    private void receiveMessage(Message message){
      switch(message.getType()){
      	case CLIENT_DISCONNECT :
      	case PING :
      	case YOUR_TURN :
      	case CARD_PLAYED :
      	case BET :
      	case CHAT_MESSAGE :
      	case GAME_SETTINGS :
      	case PLAY_STATE :
      	case DEALT_CARDS :
      	case CONNECTION_REQUEST : //Überprüfe und sende Antwort
      								//Falls ja, sende GameSettings und andere Spieler und letzten Chat
      								//Sende an alle, dass neuer Client dabei
      	case CONNECTION_ANSWER :
      	case START_GAME :
    	  default :
      }
    }
    
    private void sendMessage(Message message){
  	  try{
  		  this.output.writeObject(message);
  	  }catch(IOException e){
  		  e.printStackTrace();
  	  }
    }
    
    private void disconnect(){
        try{
            output.close();
            input.close();
            socket.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
