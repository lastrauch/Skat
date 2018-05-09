package network.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * This is a thread on the server side, to ensure that the server can be found on the local network
 * via multicast.
 * 
 * @author fkleinoe
 */
public class ServerFinderThread implements Runnable {

  // run() : void
  // Listen to broadcasted messages and answer them.

  // getInstance(Server) : ServerFinderThread
  // Get ServerFinderThread instance.

  private DatagramSocket socket;
  private boolean running;
  private static Server server;

  //////////////////////////////////////////////////////////////////////////////////////////////////
  // Internal Methods
  //////////////////////////////////////////////////////////////////////////////////////////////////
  /**
   * Listen to broadcasted messages and answer them.
   * 
   * @author fkleinoe
   */
  public void run() {
    this.running = true;
    try {
      socket = new DatagramSocket(8888, InetAddress.getByName("0.0.0.0"));
      socket.setBroadcast(true);

      while (this.running) {
        System.out.println(getClass().getName() + " >>> Ready to receive broadcast packets!");

        byte[] recvBuf = new byte[15000];
        DatagramPacket packet = new DatagramPacket(recvBuf, recvBuf.length);
        socket.receive(packet);

        System.out.println(getClass().getName() + " >>> Discovery pcket received from: "
            + packet.getAddress().getHostAddress());
        System.out.println(
            getClass().getName() + " >>> Packet received; data: " + new String(packet.getData()));

        String message = new String(packet.getData()).trim();
        if (message.equals("SKAT4_DISCOVER_REQUEST")) {
          // Servername, ip, playerAnz, maxPlayer, comment
          String answer = "SKAT4;";
          answer += server.getServerName() + ";";
          answer += server.getIp() + ";";
          answer += server.getClientConnections().size() + ";";
          answer += server.getGameSettings().getNrOfPlayers() + ";";
          answer += server.getComment() + " ;";
          byte[] sendData = answer.getBytes();

          DatagramPacket sendPacket =
              new DatagramPacket(sendData, sendData.length, packet.getAddress(), packet.getPort());
          socket.send(sendPacket);

          System.out.println(getClass().getName() + " >>> Send packet to: "
              + sendPacket.getAddress().getHostAddress());
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Get ServerFinderThread instance.
   * 
   * @author fkleinoe
   * @param s the server that should be broadcasted
   * @return ServerFinderThread to get
   */
  public static ServerFinderThread getInstance(Server s) {
    server = s;
    return ServerFinderThreadHolder.INSTANCE;
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////
  // Internal classes
  //////////////////////////////////////////////////////////////////////////////////////////////////
  /**
   * Class to instanciate a ServerFinderThread.
   * 
   * @author fkleinoe
   */
  private static class ServerFinderThreadHolder {
    private static final ServerFinderThread INSTANCE = new ServerFinderThread();
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////
  // Setter-/Getter Methods
  //////////////////////////////////////////////////////////////////////////////////////////////////
  /**
   * Set running.
   * 
   * @author fkleinoe
   * @param running to set
   */
  public void setRunning(boolean running) {
    this.running = running;
  }
}
