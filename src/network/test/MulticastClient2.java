package network.test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.MulticastSocket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import network.server.Server;

public class MulticastClient2 extends Thread {

  private static MulticastClient2 instance = new MulticastClient2();
  private List<String> mAvailableServers;
  private List<Server> mActiveServers;
  private List<String> mServerIps;
  public boolean mSearching;
  private int mPort;

  private static int mActiveServerId = 0;

  public MulticastClient2() {
    System.setProperty("java.net.preferIPv4Stack", "true");
    mSearching = true;
    mAvailableServers = new ArrayList<String>();
    mActiveServers = new ArrayList<Server>();
    mServerIps = new ArrayList<String>();
    mPort = 4446;
    System.out.println("constructor multicastclient");


  }


  public void refreshServers(){
    System.out.println("////// refresh server ///////");


    DatagramSocket socket;
    try {
      System.out.println("within refreshServers1");
      socket = new MulticastSocket(mPort);
      DatagramPacket packet;
      byte[] buf = new byte[250];
      packet = new DatagramPacket(buf, buf.length);
      System.out.println("within refreshServers2");

      try {
        socket.setSoTimeout(1000);
        socket.receive(packet);
      } catch (SocketTimeoutException e) {
        return;
      }

      System.out.println("within refreshServers3");
      String serverMessage = new String(packet.getData());
      String[] messagesFromServer = serverMessage.split(";");
      if(messagesFromServer[0].equals("SKAT4")) {
      String ip = messagesFromServer[1];

      System.out.println("array ausgabe");
      for (int i = 0; i < messagesFromServer.length; i++) {
        System.out.println(messagesFromServer[i]);
      }
      System.out.println("array length: " + messagesFromServer.length);

      if (!mServerIps.contains(ip)) {

        mServerIps.add(ip);

      }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  public void stopSearching(){
    System.out.println("stop searching");
    mSearching = false;
  }

  public void printavailableServers(){
    System.out.println("currently available servers:");
    Iterator<String> it = mServerIps.iterator();
    while (it.hasNext()) {
      System.out.println(it.next());
    }
    System.out.println();
  }

  public List<String> getmAvailableServers() {
    return mAvailableServers;
  }

  public List<Server> getmActiveServers() {
    return mActiveServers;
  }

  public static MulticastClient2 getInstance() {
    return instance;
  }
}
