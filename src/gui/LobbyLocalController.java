package gui;

import javafx.fxml.FXML;

public class LobbyLocalController {

	private GuiController main;

	public LobbyLocalController() {
		this.main = new GuiController();
	}

	@FXML
	public void settings() {
		main.displaySettings();
	}

	@FXML
	public void help() {
		main.displayHelp();
	}

	@FXML
	public void accountSettings() {
		main.displayAccountSettings();
	}

	@FXML
	public void play() {
		main.displayInGame();
	}

}
