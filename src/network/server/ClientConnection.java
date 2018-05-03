package network.server;

import logic.Player;
import network.messages.*;
import java.io.*;
import java.net.Socket;

public class ClientConnection extends Thread {
	private Server server;
	private Socket socket;
	private ObjectInputStream input; // Input from Client
	private ObjectOutputStream output; // Output from Client
	private boolean running;

	private Player player;

	public ClientConnection(Server server, Socket socket) {
		this.setName("ClientConnection");
		this.server = server;
		try {
			this.socket = socket;
			this.output = new ObjectOutputStream(socket.getOutputStream());
			this.input = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			System.out.println("Connection error");
			e.printStackTrace();
		}
	}

	public ClientConnection(Server server, Socket socket, Player player) {
		this.server = server;
		this.player = player;

		try {
			this.socket = socket;
			this.output = new ObjectOutputStream(socket.getOutputStream());
			this.input = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		this.running = true;
		try {
			Message message;
			while (this.running && (message = (Message) input.readObject()) != null) {
				System.out.println("Message empfangen: " + message.getType().name());

				receiveMessage(message);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(Message message) {
		try {
			this.output.writeObject(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void disconnect() {
		if(this.player != null) System.out.println(this.player.getName() + " CC disconnect.");
		this.running = false;
		try {
			output.close();
			input.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private synchronized void receiveMessage(Message message) {
		switch (message.getType()) {
		case PING:
			messageHandler(message);
			break;
		case YOUR_TURN:
			messageHandler(message);
			break;
		case CARD_PLAYED:
			messageHandler(message);
			break;
		case BET:
			messageHandler(message);
			break;
		case CHAT_MESSAGE:
			messageHandler(message);
			break;
		case START_GAME:
			messageHandler(message);
			break;
		case KONTRA:
			messageHandler(message);
		case REKONTRA:
			messageHandler(message);
		case DEALT_CARDS:
			dealtCardsHandler((DealtCards_Msg) message);
			break;
		case CONNECTION_REQUEST:
			connectionRequestHandler((ConnectionRequest_Msg) message);
			break;
		case GAME_SETTINGS:
			this.server.setGameSettings(((GameSettings_Msg) message).getGameSettings());
			messageHandler(message);
			break;
		case PLAY_STATE:
			this.server.setPlayState(((PlayState_Msg) message).getPlayState());
			messageHandler(message);
			break;
		case CLIENT_DISCONNECT:
			clientDisconnectHandler((ClientDisconnect_Msg) message);
			break;
		default:
		}
	}

	private void messageHandler(Message message) {
		System.out.println("Send Message: " + message.getType() + " to " + this.server.getClientConnections().size()
				+ " players.");
		for (int i = 0; i < this.server.getClientConnections().size(); i++) {
			this.server.getClientConnections().get(i).sendMessage(message);
		}
	}

	private synchronized void connectionRequestHandler(ConnectionRequest_Msg message) {
		// �berpr�fe und sende Antwort
		if (this.server.getPlayer().size() < this.server.getGameSettings().getNrOfPlayers()) {
			// Falls ja, f�ge Spieler dem Server hinzu
			// Falls ja, sende GameSettings und andere Spieler an alle
			this.player = message.getPlayer();
			System.out.println("Message send to " + message.getPlayer().getName() + ": CONNECTION_ANSWER(true)");
			this.player.setId(this.server.getNewPlayerID());
			this.sendMessage(new ConnectionAnswer_Msg(true, this.player.getId()));
			this.player = message.getPlayer();
			this.server.addPlayer(message.getPlayer());
			System.out.println("Message send because of " + message.getPlayer().getName() + ": LOBBY");
			this.messageHandler(new Lobby_Msg(this.server.getPlayer(), this.server.getGameSettings()));
		} else {
			System.out.println("Message send to " + message.getPlayer().getName() + ": CONNECTION_ANSWER(false)");
			this.sendMessage(new ConnectionAnswer_Msg(false, 0));
			this.disconnect();
		}

	}

	// TODO evtl. muss ich an alle au�er mir senden, dann die Connection
	// schlie�en und dann erst aus
	// der Liste des Servers l�schen
	private void clientDisconnectHandler(ClientDisconnect_Msg message) {
		this.server.removePlayer(message.getPlayer());
		this.server.removeClientConnection(this);
		this.messageHandler(new ClientDisconnect_Msg(message.getPlayer()));
		this.messageHandler(new Lobby_Msg(this.server.getPlayer(), this.server.getGameSettings()));
		this.disconnect();
	}

	private void dealtCardsHandler(DealtCards_Msg message) {
		System.out.println("Server received cards for: " + message.getPlayer().getName());
		for (int i = 0; i < this.server.getClientConnections().size(); i++) {
			if (this.server.getClientConnections().get(i).getPlayer().getId() == message.getPlayer().getId()) {
				this.server.getClientConnections().get(i).sendMessage(message);
				return;
			}
		}
	}

	public Player getPlayer() {
		return this.player;
	}
}
