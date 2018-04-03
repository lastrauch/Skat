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
      this.server.getServerProtocol().writeToProtocol("Error!: Connection error");
      e.printStackTrace();
    }
  }

    public void run(){
        try{
            Message message;
            boolean connected = true;
            while(connected && (message = (Message) input.readObject()) != null){
                handleMessage(message);
                this.server.getServerProtocol().writeToProtocol(message);
            }
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    private void handleMessage(Message message){
      switch(message.getType()){
        case CHAT_MESSAGE:
          System.out.println("Die Nachricht ist angekommen");
          break;
        
        case CLIENT_DISCONNECT:
          disconnect();   
          break;
        default:
            this.server.getServerProtocol().writeToProtocol("Error!: MessageType is not existent");
            break;
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
