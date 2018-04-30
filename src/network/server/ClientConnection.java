package network.server;

import logic.Player;
import network.Settings;
import network.messages.*;
import java.io.*;
import java.net.Socket;
import java.util.List;


public class ClientConnection extends Thread{
  private Server server;
  private Socket socket;
  private ObjectInputStream input;  //Eingabe vom Client
  private ObjectOutputStream output; //Ausgabe zum Client
  private boolean running;
  
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
    	this.running = true;
        try{
            Message message;
            while(this.running && (message = (Message) input.readObject()) != null){
              System.out.println("Message empfangen: " + message.getType().name());
              
              receiveMessage(message);
            }
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void sendMessage(Message message){
  	  try{
  		  this.output.writeObject(message);
  	  }catch(IOException e){
  		  e.printStackTrace();
  	  }
    }
    
    private void disconnect(){
        System.out.println(this.player.getName() + " CC disconnect.");
        this.running = false;
    	try{
            output.close();
            input.close();
            socket.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    private void receiveMessage(Message message){
        switch(message.getType()){
        	case PING: messageHandler(message); break;
        	case YOUR_TURN: messageHandler(message); break;
        	case CARD_PLAYED: messageHandler(message); break;
        	case BET: messageHandler(message); break;
        	case CHAT_MESSAGE: messageHandler(message); break;
        	case START_GAME: messageHandler(message); break;
        	case DEALT_CARDS: messageHandler(message); break;  //TODO Only send to persons that should receive it.
        	case CONNECTION_REQUEST: connectionRequestHandler((ConnectionRequest_Msg) message); break;
        	case GAME_SETTINGS:	this.server.setGameSettings(((GameSettings_Msg) message).getGameSettings());
        						messageHandler(message); break;
        	case PLAY_STATE: this.server.setPlayState(((PlayState_Msg) message).getPlayState()); 
        					 messageHandler(message); break;
        	case CLIENT_DISCONNECT:  clientDisconnectHandler((ClientDisconnect_Msg) message); break;
      	  default :
        }
      }
    
    private void messageHandler(Message message){
    	for(int i=0; i<this.server.getClientConnections().size(); i++){
    		this.server.getClientConnections().get(i).sendMessage(message);
    	}
    }
    
    private synchronized void connectionRequestHandler(ConnectionRequest_Msg message){
    	//�berpr�fe und sende Antwort
      if(this.server.getPlayer().size() < Settings.MAX_PLAYER - 1){
        	//Falls ja, f�ge Spieler dem Server hinzu
    		//Falls ja, sende GameSettings und andere Spieler an alle
    	    System.out.println(message.getPlayer().getName() + " accepted and will join.");
    		this.sendMessage(new ConnectionAnswer_Msg(true));
    		this.player = message.getPlayer();
    		this.server.addPlayer(message.getPlayer());
    		//this.sendMessage(new Lobby_Msg(this.server.getPlayer(), this.server.getGameSettings()));
    		this.messageHandler(new Lobby_Msg(this.server.getPlayer(), this.server.getGameSettings()));
    	}else{
    		this.sendMessage(new ConnectionAnswer_Msg(false));
    		this.disconnect();
    	}

    }
    
    //TODO evtl. muss ich an alle au�er mir senden, dann die Connection schlie�en und dann erst aus der Liste des Servers l�schen
    private void clientDisconnectHandler(ClientDisconnect_Msg message){
    	this.server.removePlayer(message.getPlayer());
    	this.server.removeClientConnection(this);
    	this.messageHandler(new ClientDisconnect_Msg(message.getPlayer()));
    	this.disconnect();
    }
}
