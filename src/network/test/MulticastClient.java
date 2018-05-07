package network.test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class MulticastClient {
    private DatagramSocket socket;
    private InetAddress group;
    private byte[] buf;
    private int port;
    
    public MulticastClient(int port) {
    	this.port = port;
    	findServer();
    }
    
    public void findServer() {
        try {
			socket = new DatagramSocket();
			System.out.println(getClass().getName() + " >>> Search for Servers"); 
        group = InetAddress.getByName("230.0.0.0");
        buf = "DISCOVER_SERVER_REQUEST".getBytes();
 
        DatagramPacket packet = new DatagramPacket(buf, buf.length, group, port);
        socket.send(packet);
        
        //Wait for a response
        byte[] recvBuf = new byte[15000];
        DatagramPacket receivePacket = new DatagramPacket(recvBuf, recvBuf.length);
        socket.receive(receivePacket);

        //We have a response
        System.out.println(getClass().getName() + " >>> Multicast response from server: " + receivePacket.getAddress().getHostAddress());

        //Check if the message is correct
        String message = new String(receivePacket.getData()).trim();
        if (message.equals("DISCOVER_SERVER_RESPONSE")) {
          //DO SOMETHING WITH THE SERVER'S IP (for example, store it in your controller)
          //Controller_Base.setServerIp(receivePacket.getAddress());
          System.out.println(receivePacket.getAddress());
        }
        
        socket.close();
        
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
