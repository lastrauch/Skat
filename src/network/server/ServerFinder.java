package network.server;

import java.util.List;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

public class ServerFinder {
	private List<Server> servers;
	private int port;
	private Object lock;
	private int numThreads;
	
	
	
	public ServerFinder(int port){
		this.port = port;
		this.servers = new ArrayList<Server>();
		this.lock = new Object();
		this.numThreads = 0;
		this.servers = findServers();
	}
	
	public List<Server> refresh(){
		this.servers = findServers();
		return this.servers;
	}
	
	private List<Server> findServers(){
	  System.out.println("Find Servers aufgerufen");
		this.servers.clear();
		List<String> ipRange = this.getIPRange();
		Iterator<String> it = ipRange.iterator();
		
		while(it.hasNext()){
			String range = (String) it.next();
			for(int i=0; i<255; i++){
				synchronized(this.lock){
					this.numThreads++;
					(new ServerFinderThread(range+i, this.port)).start();
				}
			}
		}
		
		synchronized (this.lock){
			if(this.numThreads > 0){
				try{
					this.lock.wait();
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}
		return this.servers;
	}
	
	private List<String> getIPRange(){
		ArrayList<String> ipRange = new ArrayList<String>();
		
		try{
			Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
			
			while(netInterfaces.hasMoreElements()){
				NetworkInterface netInter = (NetworkInterface) netInterfaces.nextElement();
				Enumeration<InetAddress> addresses = netInter.getInetAddresses();
				
				while(addresses.hasMoreElements()){
					InetAddress inetAddress = (InetAddress) addresses.nextElement();
					if(inetAddress.getHostAddress().matches("[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}") && !inetAddress.getHostAddress().matches("127.*")){
						String[] str = inetAddress.getHostAddress().split("\\.");
						ipRange.add(str[0] + "." + str[1] + "." + str[2] + ".");
					}
				}
			}
		}catch (SocketException e2){
			e2.printStackTrace();
		}
		return ipRange;
	}
	
	public List<Server> getServers(){
		return this.servers;
	}
	
	public void setServers(List<Server> servers){
		this.servers = servers;
	}
	
	public int getPort(){
		return this.port;
	}
	
	public void setPort(int port){
		this.port = port;
	}
	
}
