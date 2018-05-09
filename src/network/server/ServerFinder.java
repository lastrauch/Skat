package network.server;

import java.util.List;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Enumeration;

public class ServerFinder {
  private List<Server> servers;
  private int port;
  private DatagramSocket c;



  public ServerFinder(int port) {
    this.port = port;
    this.servers = new ArrayList<Server>();
    this.findServers();
  }

  public List<Server> refresh() {
    this.findServers();
    return this.servers;
  }

  private void findServers() {
    this.servers.clear();
    try {
      c = new DatagramSocket();
      c.setBroadcast(true);

      byte[] sendData = "SKAT4_DISCOVER_REQUEST".getBytes();

      try {
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,
            InetAddress.getByName("255.255.255.255"), 8888);
        c.send(sendPacket);
        System.out.println(
            getClass().getName() + " >>> Request packet sent to: 255.255.255.255 (DEFAULT)");
      } catch (Exception e) {
      }

      Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
      while (interfaces.hasMoreElements()) {
        NetworkInterface networkInterface = interfaces.nextElement();

        if (networkInterface.isLoopback() || !networkInterface.isUp()) {
          continue;
        }

        for (InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses()) {
          InetAddress broadcast = interfaceAddress.getBroadcast();
          if (broadcast == null) {
            continue;
          }

          try {
            DatagramPacket sendPacket =
                new DatagramPacket(sendData, sendData.length, broadcast, 8888);
            c.send(sendPacket);
          } catch (Exception e) {
          }

          System.out.println(getClass().getName() + " >>> Request packet sent to: "
              + broadcast.getHostAddress() + "; Interface: " + networkInterface.getDisplayName());
        }
      }

      System.out.println(getClass().getName()
          + " >>> Done looping over all network interfaces. Now waiting for a reply!");

      try {
      c.setSoTimeout(1000);
      byte[] recvBuf = new byte[15000];
      DatagramPacket receivePacket = new DatagramPacket(recvBuf, recvBuf.length);
      c.receive(receivePacket);
      
      System.out.println(getClass().getName() + " >>> Broadcast response from server: "
          + receivePacket.getAddress().getHostAddress());

      String msg = new String(receivePacket.getData()).trim();
      System.out.println("!!!!!!!!!!!!!!!!!!!<><><><><><><>!!!!!!!!!!!! " + msg);
      String[] message = msg.split(";");
      System.out.println(message[0]);
      if (message[0].equals("SKAT4")) {
        //TODO
        for(int i=0; i<message.length; i++) {
          System.out.println(message[i]);
        }
      }
      
      }catch (SocketTimeoutException e) {
      }

      c.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }



  public List<Server> getServers() {
    return this.servers;
  }

  public void setServers(List<Server> servers) {
    this.servers = servers;
  }

  public int getPort() {
    return this.port;
  }

  public void setPort(int port) {
    this.port = port;
  }

}
