package network.test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ServerThread implements Runnable {
  private static ServerThread me;
  private Server server;
  private int port;
  private DatagramSocket socket = null;
  private boolean running;

  public ServerThread(Server server) {
    this.server = server;
    this.port = this.server.getPort();
  }

  public static ServerThread getInstance(Server server) {
    if (me == null) {
      me = new ServerThread(server);
    }
    return me;
  }

  public void run() {
    this.running = true;
    try {
      socket = new DatagramSocket();
      // socket.setBroadcast(true);
      System.out.println(getClass().getName() + " >>> Multicast Server start");
      InetAddress group = InetAddress.getByName("224.0.0.1");
      while (this.running) {
        Thread.sleep(100);
        byte[] data = new byte[250];
        data = "SKAT4".getBytes();
        DatagramPacket sendPacket = new DatagramPacket(data, data.length, group, this.port);
        System.out.println(getClass().getName() + " >>> Send message");
        this.socket.send(sendPacket);
      }

      socket.close();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
