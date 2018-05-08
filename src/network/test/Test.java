package network.test;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import logic.GameSettings;
import network.server.Server;
import network.test.Client2;
import network.test.Server2;

public class Test {

	public static void test() {
//		Server3 server = new Server3("Felix", 3000);
//		Client3 client = new Client3(3000);
		
		try {
			Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
			int anz = 0;
			while(netInterfaces.hasMoreElements()) {
				anz++;
				NetworkInterface netInter = netInterfaces.nextElement();
				Enumeration<InetAddress> addresses = netInter.getInetAddresses();
				 while (addresses.hasMoreElements()) {
					 InetAddress inetAddress = (InetAddress) addresses.nextElement();
					 System.out.println(inetAddress.getHostAddress());
				 }
			}
			System.out.println(">>>>>> Anzahl: " + anz);

		} catch (SocketException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			System.out.println(" >>> Localhost: " + InetAddress.getLocalHost());
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
		
//		MulticastServer server = new MulticastServer("Felix", 4446);
//		try {
//			Thread.sleep(10);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		MulticastClient client = new MulticastClient(4446);
//		client.findServer();
		
		
		Server server = new Server("Felix Server", 4446, new GameSettings(), "Hallo");
		MulticastServer2 mcs2 = new MulticastServer2(server);
		mcs2.start();
		MulticastClient2 mcc2 = new MulticastClient2();
		mcc2.refreshServers();
		
	    MulticastClient2.getInstance().refreshServers();
	    MulticastClient2.getInstance().printavailableServers();
	}

}
