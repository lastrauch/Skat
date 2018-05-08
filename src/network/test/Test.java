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


		
		
		Server server = new Server("Felix Server", 4446, new GameSettings(), "Hallo");
		MulticastServer2 mcs2 = new MulticastServer2(server);
		mcs2.start();
		MulticastClient2 mcc2 = new MulticastClient2();
		mcc2.refreshServers();
		
	    MulticastClient2.getInstance().refreshServers();
	    MulticastClient2.getInstance().printavailableServers();
	}

}
