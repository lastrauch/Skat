package network.test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerFinderThread3 implements Runnable{
	private static ServerFinderThread3 me;
	private DatagramSocket socket;
	private int port;
	private boolean running;

	public ServerFinderThread3(int port) {
		
	}
	
	public static ServerFinderThread3 getInstance(int port) {
		if(me == null) {
			me = new ServerFinderThread3(port);
		}
		return me;
	}
	  @Override
	  public void run() {
		  this.running = true;
	    try {
	      //Keep a socket open to listen to all the UDP trafic that is destined for this port
	      socket = new DatagramSocket(this.port);

	      while (running) {
	        System.out.println(getClass().getName() + ">>>Ready to receive multicast packets!");

	        //Receive a packet
	        byte[] recvBuf = new byte[15000];
	        DatagramPacket packet = new DatagramPacket(recvBuf, recvBuf.length);
	        socket.receive(packet);

	        //Packet received
	        System.out.println(getClass().getName() + ">>>Discovery packet received from: " + packet.getAddress().getHostAddress());
	        System.out.println(getClass().getName() + ">>>Packet received; data: " + new String(packet.getData()));

	        //See if the packet holds the right command (message)
	        String message = new String(packet.getData()).trim();
	        if (message.equals("DISCOVER_SERVER_REQUEST")) {
	          byte[] sendData = "DISCOVER_SERVER_RESPONSE".getBytes();

	          //Send a response
	          DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, packet.getAddress(), packet.getPort());
	          socket.send(sendPacket);

	          System.out.println(getClass().getName() + ">>>Sent packet to: " + sendPacket.getAddress().getHostAddress());
	        }
	      }
	    } catch (IOException ex) {
	      Logger.getLogger(ServerFinderThread2.class.getName()).log(Level.SEVERE, null, ex);
	    }
	  }
}
