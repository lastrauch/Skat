package network.test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import network.server.Server;


public class MulticastServer2 extends Thread {

  private int mMulticastPort;
  private int mBroadcast;
  private Server mServer;
  private DatagramSocket mSocket;
  private boolean mWaitingForPlayers;
  private String mServerIp;
  private int mServerPort;
  private String mServerName;
  private int mPlayerCount;

  public MulticastServer2(Server s) {
    mServer = s;
    mWaitingForPlayers = true;
    mMulticastPort = 4446;
    mBroadcast = 20;

    mServerIp = mServer.getIP();
    mServerPort = mServer.getPort();
    mServerName = mServer.getServerName();
    mPlayerCount = mServer.getClientConnections().size();
    System.out.println("constrcutor");
  }


  public void run() {
    System.out.println("run");
    while (mWaitingForPlayers) {
      try {
        Thread.sleep(mBroadcast);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
//      System.out.println("run2");
      try {
        //mSocket = new DatagramSocket();
        mSocket = new MulticastSocket(mServerPort);
        //mSocket.setBroadcast(true);
        byte[] buf;
        if(true) {
          StringBuffer message = new StringBuffer("SKAT4;");
          message.append(mServerIp + ";");
          message.append(mServer.getClientConnections().size() + ";");
          message.append(mServerName + ";");

          buf = message.toString().getBytes();
          InetAddress group = InetAddress.getByName("224.0.0.1");
          DatagramPacket packet;
          packet = new DatagramPacket (buf, buf.length, group, mMulticastPort);
          mSocket.send(packet);
        }
      } catch (UnknownHostException e) {
        e.printStackTrace();
      } catch (SocketException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }

    }
    mSocket.close();
  }


//  public static void main(String [] args){
//    MulticastServer ms = new MulticastServer(new Server("localhost", 1234, "test-server"));
//    ms.run();
//  }
}

