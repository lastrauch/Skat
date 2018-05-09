package network.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import network.Settings;

/**
 * Client side of a local network discovery to find skat server.
 * 
 * @author fkleinoe
 */
public class ServerFinder {
  
  // refresh() : List<Server>
  // Refresh the list of skat servers in the network.
  
  // findServers() : void
  // Searches for skat servers in the local network.
  
  private List<Server> servers;
  private int port;
  private DatagramSocket socket;

  //////////////////////////////////////////////////////////////////////////////////////////////////
  // Constructor
  //////////////////////////////////////////////////////////////////////////////////////////////////
  /**
   * Constructor.
   * 
   * @author fkleinoe
   * @param port of the server
   */
  public ServerFinder(int port) {
    this.port = port;
    this.servers = new ArrayList<Server>();
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////
  // Internal Methods
  //////////////////////////////////////////////////////////////////////////////////////////////////
  /**
   * Refresh the list of skat servers in the network.
   * 
   * @author fkleinoe
   * @return List(Server) to get
   */
  public List<Server> refresh() {
    System.out.println("asdasdasdasdasdasdasdasdasd refresh aufgerufen");
    this.servers.clear();
    this.findServers();
    System.out.println("asdasdasdasdasdasdasdasdasd so viele sörver " + this.servers.size());
    return this.servers;
  }

  /**
   * Searches for skat servers in the local network.
   * 
   * @author fkleinoe
   */
  private void findServers() {
    
    try {
      socket = new DatagramSocket();
      socket.setBroadcast(true);

      byte[] sendData = "SKAT4_DISCOVER_REQUEST".getBytes();

      try {
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,
            InetAddress.getByName("255.255.255.255"), 8888);
        socket.send(sendPacket);
        System.out.println(
            getClass().getName() + " >>> Request packet sent to: 255.255.255.255 (DEFAULT)");
      } catch (Exception e) {
        System.out.println("Could not send packet");
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
            socket.send(sendPacket);
          } catch (Exception e) {
            System.out.println("Could not send packet");
          }

          System.out.println(getClass().getName() + " >>> Request packet sent to: "
              + broadcast.getHostAddress() + "; Interface: " + networkInterface.getDisplayName());
        }
      }

      System.out.println(getClass().getName()
          + " >>> Done looping over all network interfaces. Now waiting for a reply!");

      try {
        socket.setSoTimeout(1000);
        byte[] recvBuf = new byte[15000];
        DatagramPacket receivePacket = new DatagramPacket(recvBuf, recvBuf.length);
        socket.receive(receivePacket);

        System.out.println(getClass().getName() + " >>> Broadcast response from server: "
            + receivePacket.getAddress().getHostAddress());

        String msg = new String(receivePacket.getData()).trim();
        System.out.println("!!!!!!!!!!!!!!!!!!!<><><><><><><>!!!!!!!!!!!! " + msg);
        String[] message = msg.split(";");
        System.out.println(message[0]);
        if (message[0].equals("SKAT4")) {
          // Servername, ip, playerAnz, maxPlayer, comment
          String serverName = message[1];
          String ip = message[2];
          int numPlayer = Integer.parseInt(message[3]);
          int maxPlayer = Integer.parseInt(message[4]);
          String comment = message[5];
          Server server = new Server(serverName, Settings.PORT, numPlayer, maxPlayer, comment);
          server.setIp(ip);
          System.out.println("So viele server haben wir bisher: " + this.servers.size());
          this.servers.add(server);
        }

      } catch (SocketTimeoutException e) {
        System.out.println("No server were found");
      }

      socket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////
  // Setter-/Getter Methods
  //////////////////////////////////////////////////////////////////////////////////////////////////
  /**
   * Get servers.
   * 
   * @author fkleinoe
   * @return List(Server) to get
   */
  public List<Server> getServers() {
    return this.servers;
  }

  /**
   * Set servers.
   * 
   * @author fkleinoe
   * @param servers to set
   */
  public void setServers(List<Server> servers) {
    this.servers = servers;
  }

  /**
   * Get port.
   * 
   * @author fkleinoe
   * @return int to get
   */
  public int getPort() {
    return this.port;
  }

  /**
   * Set port.
   * 
   * @author fkleinoe
   * @param port to set
   */
  public void setPort(int port) {
    this.port = port;
  }

}
