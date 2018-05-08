package network.test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastFinder implements Runnable{
	private static MulticastFinder me;
	private String serverName;
	private int port;
	private MulticastSocket socket = null;
	private byte[] data = new byte[15000];
	private boolean running;
	
	public MulticastFinder(String serverName, int port) {
		this.serverName = serverName;
		this.port = port;
	}
	
	public static MulticastFinder getInstance(String serverName, int port) {
		if(me == null) {
			me = new MulticastFinder(serverName, port);
		}
		return me;
	}
	
	
    public void run() {
    	this.running = true;
        try {
			socket = new MulticastSocket(this.port);
			socket.setBroadcast(true);
			System.out.println(getClass().getName() + " >>> Multicast Server start");
        InetAddress group = InetAddress.getByName("224.0.0.1");
        socket.joinGroup(group);
        while (this.running) {
          System.out.println(getClass().getName() + " >>> Multicast Server listening");
            DatagramPacket packet = new DatagramPacket(data, data.length);
            socket.receive(packet);
            
            String msg = new String(packet.getData());
            System.out.println(getClass().getName() + " >>> Message received: " + msg.trim());
            		if(msg.trim().equals("SKAT4") || msg.trim().equals("DISCOVER_SERVER_REQUESTE")){
            		  System.out.println(getClass().getName() + " >>> Server request from: " +  packet.getAddress());
            			InetAddress address = packet.getAddress();
            			//TODO apply infos
            			//data = this.serverName.getBytes();
            			data = "SKAT4".getBytes();
            			DatagramPacket sendPacket = new DatagramPacket(data, data.length, address, packet.getPort());
            			this.socket.send(sendPacket);
            		}
        }
        socket.leaveGroup(group);
        socket.close();
        } catch (IOException e) {
			e.printStackTrace();
		}
    }
}
