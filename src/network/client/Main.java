package network.client;

import logic.Player;
import network.server.Server;

public class Main {

  public static void main(String[] args) {
    Player player = new Player("Felix");
    Client c = new Client(new Server("Felix Server", 5000), player, false, 5000);

  }

}
