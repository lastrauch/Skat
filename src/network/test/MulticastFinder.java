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
		
        InetAddress group = InetAddress.getByName("224.0.0.0");
        socket.joinGroup(group);
        while (this.running) {
            DatagramPacket packet = new DatagramPacket(data, data.length);
            socket.receive(packet);
            
            String msg = new String(packet.getData());
            		if(msg.trim().equals("DISCOVER_SERVER_REQUEST")){
            			InetAddress address = packet.getAddress();
            			//TODO apply infos
            			data = this.serverName.getBytes();
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
