package network.test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class Client {
  private MulticastSocket socket;
  private InetAddress group;
  private int port;

  public Client(int port) {
    this.port = port;
  }

  public void findServer() {
    System.out.println(getClass().getName() + " >>> Search for Server");
    try {
      socket = new MulticastSocket();
      group = InetAddress.getByName("224.0.0.1");
      socket.joinGroup(group);
      while(true) {
      byte[] data = new byte[250];
      DatagramPacket receivePacket = new DatagramPacket(data, data.length);

      //socket.setSoTimeout(1000);
      socket.receive(receivePacket);

      String message = new String(receivePacket.getData()).trim();
      System.out.println(getClass().getName() + " >>> Multicast response from server: "
          + receivePacket.getAddress().getHostAddress());
      System.out.println(getClass().getName() + " >>> Message: " + message);
      }

    } catch (SocketTimeoutException e) {
      e.printStackTrace();
    } catch (SocketException e) {
      e.printStackTrace();
    } catch (UnknownHostException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
