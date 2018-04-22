package network.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import logic.Player;
import network.messages.Message;
import network.messages.*;
import network.server.Server;
import interfaces.NetworkLogic;

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
	  	case YOUR_TURN : NetworkLogic.receiveYourTurn();
	  					 break;
	  	case CARD_PLAYED : CardPlayed_Msg msg2 = (CardPlayed_Msg) message;
	  					   NetworkLogic.receiveCardPlayed(msg2.getPlayer(), msg2.getCard());
	  					   break;
	  	case BET : Bet_Msg msg3 = (Bet_Msg) message;
	  			   NetworkLogic.receiveBet(msg3.getPlayer(), msg3.getBet());
	  			   break;
	  	case CHAT_MESSAGE : ChatMessage_Msg msg4 = (ChatMessage_Msg) message;
	  						NetworkLogic.receiveChatMessage(msg4.getPlayer(), msg4.getMsg());
	  						break;
	  	case GAME_SETTINGS : GameSettings_Msg msg5 = (GameSettings_Msg) message;
	  						 NetworkLogic.receiveGameSettings(msg5.getGameSettings());
	  						 break;
	  	case PLAY_SETTINGS : PlaySettings_Msg msg6 = (PlaySettings_Msg) message;
	  						 NetworkLogic.receivePlayState(msg6.getPlayState());
	  						 break;
	  	case DEALT_CARDS : DealtCards_Msg msg7 = (DealtCards_Msg) message;
	  					   NetworkLogic.receiveCards(msg7.getCards());
	  					   break;
	  	case CONNECTION_ANSWER : ConnectionAnswer_Msg msg8 = (ConnectionAnswer_Msg) message;
	  							 NetworkLogic.receiveConnectionRequestAsnwer(msg8.getAccepted());
	  							 break;
	  	case LOBBY : Lobby_Msg msg9 = (Lobby_Msg) message;
	  				 NetworkLogic.receiveLobby(msg9.getServer(), msg9.getHost(), msg9.getPlayer(), msg9.getGameSettings());
	  				 break;
	  	case START_GAME : NetworkLogic.receiveStartGame();
	  					  break;
	  	case CLIENT_DISCONNECT : ClientDisconnect_Msg msg11 = (ClientDisconnect_Msg) message;
	  							 NetworkLogic.receivePlayerDisconnected(msg11.getPlayer());
	  							 break;
	  	default :
	  		break;
	  }
  }
}
