package network.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import logic.ClientLogic;
import logic.Player;
import network.messages.Message;
import network.messages.*;
import network.server.Server;

public class Client extends Thread{
  private Server server;
  private int port;
  private Socket socket;
  private Player owner;
  private ObjectOutputStream output; //Ausgabe zum Server
  private ObjectInputStream input; //Eingabe vom Server
  private ClientLogic logic;
  
  public Client(Server server, Player player, int port, ClientLogic logic){
    this.server = server;
    this.owner = player;
    this.port = port;
    this.logic = logic;
    
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
          }
      }catch(ClassCastException e){
        System.out.println("Client run(); ClassCastException");
      }catch(ClassNotFoundException e){
        System.out.println("Client run(): ClassNotFoundException");
          e.printStackTrace();
      }catch(IOException e){
        System.out.println("Client run(): IOException");
          e.printStackTrace();
      }
  }
 
  private boolean connect(){
    try{
      this.socket = new Socket(server.getIP(), port);
      this.output = new ObjectOutputStream(socket.getOutputStream());
      this.input = new ObjectInputStream(socket.getInputStream());
      System.out.println("Connection established");
    } catch (UnknownHostException e){
      e.printStackTrace();
      return false;
    }catch(IOException e){
      e.printStackTrace();
      return false;
    }
    return true;
  }
  
  public void disconnect(){
    System.out.println(this.owner.getName() + " client disconnect.");
    // TODO
    try{
        this.output.writeObject(new ClientDisconnect_Msg(this.owner));
        this.output.close();
        this.input.close();
        this.socket.close();
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
  
  public boolean requestConnection() {
	   try{
		   output.writeObject(new ConnectionRequest_Msg(this.owner));
		   this.start();
			Message serverOutput;

	                  if((serverOutput = (Message) input.readObject()) != null){
	                        if(serverOutput.getType() == MessageType.CONNECTION_ANSWER){
	                            ConnectionAnswer_Msg m = (ConnectionAnswer_Msg) serverOutput;
	                            return m.getAccepted();
	                        }else{
	                            System.out.println("Message from server is invalid!");
	                        }
	                    }
	   }catch(ArrayIndexOutOfBoundsException e){
		   e.printStackTrace();
	   }catch(ClassNotFoundException e){
	     System.out.println("Input war keine Message (Client)");
		   e.printStackTrace();
	   }catch(IOException e){
		   e.printStackTrace();
	   }
	   return false;
  }
  
  //TODO Nachrichten eventuell verwerfen, wenn nicht ben�tigt
  private void receiveMessage(Message message){
	  switch(message.getType()){
	  	case YOUR_TURN : logic.receiveYourTurn();
	  					 break;
	  	case CARD_PLAYED : CardPlayed_Msg msg2 = (CardPlayed_Msg) message;
	  					   logic.receiveCardPlayed(msg2.getPlayer(), msg2.getCard());
	  					   break;
	  	case BET : Bet_Msg msg3 = (Bet_Msg) message;
	  			   logic.receiveBet(msg3.getPlayer(), msg3.getBet());
	  			   break;
	  	case CHAT_MESSAGE : ChatMessage_Msg msg4 = (ChatMessage_Msg) message;
	  						logic.receiveChatMessage(msg4.getPlayer(), msg4.getMsg());
	  						break;
	  	case GAME_SETTINGS : GameSettings_Msg msg5 = (GameSettings_Msg) message;
	  						 logic.receiveGameSettings(msg5.getGameSettings());
	  						 break;
	  	case PLAY_STATE : PlayState_Msg msg6 = (PlayState_Msg) message;
	  						 logic.receivePlayState(msg6.getPlayState());
	  						 break;
	  	case DEALT_CARDS : DealtCards_Msg msg7 = (DealtCards_Msg) message;
	  					   logic.receiveCards(msg7.getCards(), msg7.getPlayState());
	  					   break;
	  	case LOBBY : Lobby_Msg msg9 = (Lobby_Msg) message;
	  				 logic.receiveLobby(msg9.getPlayer(), msg9.getGameSettings());
	  				 break;
	  	case START_GAME : logic.receiveStartGame();
	  					  break;
	  	case CLIENT_DISCONNECT : ClientDisconnect_Msg msg11 = (ClientDisconnect_Msg) message;
	  							 logic.receivePlayerDisconnected(msg11.getPlayer());
	  							 break;
	  	default :
	  		break;
	  }
  }
}
