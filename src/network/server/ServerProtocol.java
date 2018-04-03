package network.server;

import network.messages.Message;

public class ServerProtocol {
  private static String protocol;
  
  public ServerProtocol(){
    protocol = "";
  }
  
  public void writeToProtocol(Message message){
    protocol += "\n" + message.toString();
  }
  
  public void writeToProtocol(String str){
    protocol += "\n" + str;
  }
  
  public String getProtocol(){
    return protocol;
  }
  
  public void displayProtocol(){
    System.out.println(protocol);
  }
}
