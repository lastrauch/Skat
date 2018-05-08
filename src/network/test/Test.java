package network.test;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

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
		MulticastServer server = new MulticastServer("Felix", 3000);
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MulticastClient client = new MulticastClient(3000);
	}

}
