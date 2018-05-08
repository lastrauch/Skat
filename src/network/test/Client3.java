package network.test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client3 extends Thread {
  private Server3 server;
  private int port;
  private Socket socket;
  private ObjectOutputStream output; // Output to Server
  private ObjectInputStream input; // Input from Server

  public Client3(Server3 server, int port) {
    this.server = server;
    this.port = port;

    boolean connectionEstablished = connect();
    if (!connectionEstablished) {
      System.out.println("Sorry: No connection to " + server.getServerName() + ": " + port);
    }
  }

 public Client3(int port) {
	 this.port = port;
	 
	 findServer();
 }

  private boolean connect() {
    try {
      this.socket = new Socket(server.getIP(), port);
      this.output = new ObjectOutputStream(socket.getOutputStream());
      this.input = new ObjectInputStream(socket.getInputStream());
      System.out.println("Socket Connection established");
    } catch (UnknownHostException e) {
      e.printStackTrace();
      return false;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }
  
  public void findServer() {
	  //this.servers.clear();
	  DatagramSocket c;
      // Find the server using UDP multicast
      try {
        //Open a random port to send the package
        c = new DatagramSocket();
        c.setBroadcast(true);

        byte[] sendData = "DISCOVER_SERVER_REQUEST".getBytes();
        
        // Get all possible IPs
        List<String> ipRange = this.getIPRange();
        Iterator<String> it = ipRange.iterator();
        // Multicast the message over all the network interfaces
        while(it.hasNext()) {
        	String range = (String) it.next();
        	//System.out.println(InetAddress.getByName(range));
        	try {
        	
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName(range), this.port);
                System.out.println(getClass().getName() + ">>> Request packet sent to: " + range);
                c.send(sendPacket);
                System.out.println(getClass().getName() + ">>> Request packet sent to: " + range);
              } catch (Exception e) {
              }
        }

        System.out.println(getClass().getName() + ">>> Done looping over all network interfaces. Now waiting for a reply!");

        //Wait for a response
        byte[] recvBuf = new byte[15000];
        DatagramPacket receivePacket = new DatagramPacket(recvBuf, recvBuf.length);
        c.receive(receivePacket);

        //We have a response
        System.out.println(getClass().getName() + ">>> Broadcast response from server: " + receivePacket.getAddress().getHostAddress());

        //Check if the message is correct
        String message = new String(receivePacket.getData()).trim();
        if (message.equals("DISCOVER_FUIFSERVER_RESPONSE")) {
          //DO SOMETHING WITH THE SERVER'S IP (for example, store it in your controller)
          //Controller_Base.setServerIp(receivePacket.getAddress());
          System.out.println(receivePacket.getAddress());
        }

        //Close the port!
        c.close();
      } catch (IOException ex) {
        Logger.getLogger(Client2.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
  
  private List<String> getIPRange() {
	    ArrayList<String> ipRange = new ArrayList<String>();
	    System.out.println(getClass().getName() + ">>> Create ipRange");
	    try {
	      Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();

	      while (netInterfaces.hasMoreElements()) {
	        NetworkInterface netInter = (NetworkInterface) netInterfaces.nextElement();
	        Enumeration<InetAddress> addresses = netInter.getInetAddresses();

	        while (addresses.hasMoreElements()) {
	          InetAddress inetAddress = (InetAddress) addresses.nextElement();
	          if (inetAddress.getHostAddress()
	              .matches("[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}")
	              && !inetAddress.getHostAddress().matches("127.*")) {
	            String[] str = inetAddress.getHostAddress().split("\\.");
	            ipRange.add(str[0] + "." + str[1] + "." + str[2] + ".");
	            System.out.println(inetAddress.getHostAddress());
	          }
	        }
	      }
	    } catch (SocketException e2) {
	      e2.printStackTrace();
	    }
	    System.out.println(getClass().getName() + ">>> ipRange has size " + ipRange.size());
	    return ipRange;
	  }
  
}
