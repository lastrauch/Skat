package network.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ServerFinderThread extends Thread {
  private DatagramSocket socket;
  private String serverName;
  private int port;
  private boolean running;

  public ServerFinderThread(String serverName, int port) {
    this.serverName = serverName;
    this.port = port;
  }

  public void run() {
    this.running = true;

    byte[] data = new byte[1024];

    try {
      this.socket = new DatagramSocket(this.port);
      while (this.running) {
        DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
        this.socket.receive(packet);
        String msg = new String(packet.getData());
        if (msg.trim().equals("ping")) {
          InetAddress address = packet.getAddress();
          data = this.serverName.getBytes();
          DatagramPacket sendPacket =
              new DatagramPacket(data, data.length, address, packet.getPort());
          this.socket.send(sendPacket);
        }
      }
    } catch (SocketException e) {
      if (e.getMessage().equals("socket closed")) {
        return;
      }
    } catch (IOException e2) {
      e2.printStackTrace();
    }
  }

  public void close() {
    this.socket.close();
  }
}
