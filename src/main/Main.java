package main;

import logic.*;
import network.client.Client;
import network.server.Server;

public class Main {

	public static void main(String[] args) {
		System.out.println("Hello World");
		new Server("Felix Server", 5000);
		
		Player player = new Player("Felix");
	    Client c = new Client(new Server("Felix Server", 5000), player, false, 5000);
	}

}
