package network.test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

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
        group = InetAddress.getByName("224.0.0.1");
        buf = "SKAT4".getBytes();
        
        List<String> ipRange = getIPRange();
        System.out.println(getClass().getName() + " >>> IPRange size: " + ipRange.size());
//        for(int i=0; i<ipRange.size(); i++) {
//        	System.out.println(ipRange.get(i));
//        }
        Iterator it = ipRange.iterator();
        while(it.hasNext()) {
        	String address = (String) it.next();
        	//for(int i=0; i<255; i++) {
        		System.out.println(getClass().getName() + " >>> Check IP: " + address);
                //DatagramPacket packet = new DatagramPacket(buf, buf.length, InetAddress.getByName(address + i), port);
                DatagramPacket packet = new DatagramPacket(buf, buf.length, group, port);
                try {
                  socket.setSoTimeout(1000);
                  socket.send(packet);
                }catch(IOException e) {
                  
                }
                

                
 
        //DatagramPacket packet = new DatagramPacket(buf, buf.length, group, port);
        //socket.send(packet);
        
        //Wait for a response
        byte[] recvBuf = new byte[100];
        DatagramPacket receivePacket = new DatagramPacket(recvBuf, recvBuf.length);
        try {
          socket.setSoTimeout(1000);
          socket.receive(receivePacket);
          } catch (SocketTimeoutException e) {
            return;
         }
          
          
        //We have a response
        System.out.println(getClass().getName() + " >>> Multicast response from server: " + receivePacket.getAddress().getHostAddress());

        //Check if the message is correct
        String message = new String(receivePacket.getData()).trim();
        if (message.equals("DISCOVER_SERVER_RESPONSE")) {
          //DO SOMETHING WITH THE SERVER'S IP (for example, store it in your controller)
          //Controller_Base.setServerIp(receivePacket.getAddress());
          System.out.println(receivePacket.getAddress());
        }
        	
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
    
    private List<String> getIPRange() {
        ArrayList<String> ipRange = new ArrayList<String>();

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
              }
            }
          }
        } catch (SocketException e2) {
          e2.printStackTrace();
        }
        return ipRange;
      }
}
