package network.test;

import logic.GameSettings;
import network.server.Server;

public class Test {

	public static void test() {
	  
//	  Server s = new Server("Felix Server", 4446);
//	  
//	  try {
//      Thread.sleep(500);
//    } catch (InterruptedException e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
//	  
//	  Client c = new Client(4446);
//	  c.findServer();

	    MulticastServer server = new MulticastServer("Felix", 4446);
	    MulticastClient client = new MulticastClient(4446);

		
		
//		Server server = new Server("Felix Server", 4446, new GameSettings(), "Hallo");
//		MulticastServer2 mcs2 = new MulticastServer2(server);
//		mcs2.start();
//		MulticastClient2 mcc2 = new MulticastClient2();
//		mcc2.refreshServers();
//		
//	    MulticastClient2.getInstance().refreshServers();
//	    MulticastClient2.getInstance().printavailableServers();
	}

}
