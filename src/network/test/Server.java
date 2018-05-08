package network.test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server implements Runnable {

  private DatagramSocket socket;
  
  public void run() {
    try {
      socket = new DatagramSocket(8888, InetAddress.getByName("0.0.0.0"));
      socket.setBroadcast(true);
      
      while(true) {
        System.out.println(getClass().getName() + " >>> Ready to receive broadcast packets!");
        
        byte[] recvBuf = new byte[15000];
        DatagramPacket packet = new DatagramPacket(recvBuf, recvBuf.length);
        socket.receive(packet);
        
        System.out.println(getClass().getName() + " >>> Discovery pcket received from: " + packet.getAddress().getHostAddress());
        System.out.println(getClass().getName() + " >>> Packet received; data: " + new String(packet.getData()));
        
        String message = new String(packet.getData()).trim();
        if(message.equals("DISCOVERY_FUIFSERVER_REQUEST")) {
          byte[] sendData = "DISCOVERY_FUIFSERVER_RESPONSE".getBytes();
          
          DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, packet.getAddress(), packet.getPort());
          socket.send(sendPacket);
          
          System.out.println(getClass().getName() + " >>> Send packet to: " + sendPacket.getAddress().getHostAddress());
        }
      }
    }catch (IOException e) {
      Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
    }
  }
  
  public static Server getInstance() {
    return ServerHolder.INSTANCE;
  }
  
  private static class ServerHolder{
    private static final Server INSTANCE = new Server();
  }
  
  
}
