package network.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServerFinderThread implements Runnable {
private DatagramSocket socket;
private boolean running;
private static Server server;
  
  public void run() {
    this.running = true;
    try {
      socket = new DatagramSocket(8888, InetAddress.getByName("0.0.0.0"));
      socket.setBroadcast(true);
      
      while(this.running) {
        System.out.println(getClass().getName() + " >>> Ready to receive broadcast packets!");
        
        byte[] recvBuf = new byte[15000];
        DatagramPacket packet = new DatagramPacket(recvBuf, recvBuf.length);
        socket.receive(packet);
        
        System.out.println(getClass().getName() + " >>> Discovery pcket received from: " + packet.getAddress().getHostAddress());
        System.out.println(getClass().getName() + " >>> Packet received; data: " + new String(packet.getData()));
        
        String message = new String(packet.getData()).trim();
        if(message.equals("SKAT4_DISCOVER_REQUEST")) {
          // Servername, ip, playerAnz, maxPlayer, comment
          String answer = "SKAT4;";
          answer += server.getServerName() + ";";
          answer += server.getIP() + ";";
          answer += server.getClientConnections().size() + ";";
          answer += server.getGameSettings().getNrOfPlayers() + ";";
          answer += server.getComment();
          byte[] sendData = answer.getBytes();
          
          DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, packet.getAddress(), packet.getPort());
          socket.send(sendPacket);
          
          System.out.println(getClass().getName() + " >>> Send packet to: " + sendPacket.getAddress().getHostAddress());
        }
      }
    }catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public static ServerFinderThread getInstance(Server s) {
    server = s;
    return ServerFinderThreadHolder.INSTANCE;
  }
  
  private static class ServerFinderThreadHolder{
    private static final ServerFinderThread INSTANCE = new ServerFinderThread();
  }
  
  public void setRunning(boolean running) {
    this.running = running;
  }
}
