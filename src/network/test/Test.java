package network.test;

import network.test.Client2;
import network.test.Server2;

public class Test {

	public static void test() {
		Server2 server = new Server2("Felix", 3000);
		Client2 client = new Client2(3000);

	}

}
