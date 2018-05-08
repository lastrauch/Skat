package network.test;

import network.test.Server;

public class Test {

	public static void test() {
	  


//	    MulticastServer server = new MulticastServer("Felix", 4446);
//	    MulticastClient client = new MulticastClient(4446);

		Thread server = new Thread(Server.getInstance());
		server.start();
		Client c = new Client();
		

	}

}
