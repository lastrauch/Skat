package network.test;

import java.net.DatagramSocket;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.logging.*;

public class Client {
  private DatagramSocket c;

public Client() {
  findServer();
}

  public void findServer() {
    try {
      c = new DatagramSocket();
      c.setBroadcast(true);
      
      byte[] sendData = "DISCOVER_FUIFSERVER_REQUEST".getBytes();
      
      try {
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("255.255.255.255"), 8888);
        c.send(sendPacket);
        System.out.println(getClass().getName() + " >>> Request packet sent to: 255.255.255.255 (DEFAULT)");
      }catch (Exception e) {
      }
      
      Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
      while(interfaces.hasMoreElements()) {
        NetworkInterface networkInterface = interfaces.nextElement();
        
        if(networkInterface.isLoopback() || !networkInterface.isUp()) {
          continue;
        }
        
        for(InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses()) {
          InetAddress broadcast = interfaceAddress.getBroadcast();
          if(broadcast == null) {
            continue;
          }
        
        try {
          DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, broadcast, 8888);
          c.send(sendPacket);
        } catch (Exception e) {
        }
        
        System.out.println(getClass().getName() + " >>> Request packet sent to: " + broadcast.getHostAddress() + "; Interface: " + networkInterface.getDisplayName());
      }
    }
      
      System.out.println(getClass().getName() + " >>> Done looping over all network interfaces. Now waiting for a reply!");
      
      byte[] recvBuf = new byte[15000];
      DatagramPacket receivePacket = new DatagramPacket(recvBuf, recvBuf.length);
      c.receive(receivePacket);
      
      System.out.println(getClass().getName() + " >>> Broadcast response from server: " + receivePacket.getAddress().getHostAddress());
      
      String message = new String(receivePacket.getData()).trim();
      if(message.equals("DISCOVER_FUIFSERVER_RESPONSE")) {
        // TODO do something with data
      }
      
      c.close();
  }catch (IOException e) {
    e.printStackTrace();
  }
}
}
