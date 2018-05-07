package network.test;

import network.test.Client2;
import network.test.Server2;

public class Test {

	public static void test() {
//		Server3 server = new Server3("Felix", 3000);
//		Client3 client = new Client3(3000);
		
		
		MulticastServer server = new MulticastServer("Felix", 3000);
		MulticastClient client = new MulticastClient(3000);
	}

}
