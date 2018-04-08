package gui;

import javafx.fxml.FXML;

public class ChooseGameController {

	@FXML
	private GuiController main;

	public ChooseGameController() {
		this.main = new GuiController();
	}

	@FXML
	public void SinglePlayer() {
		main.displayLobbyLocal();
	}

	@FXML
	public void MultiPlayer() {
		main.displayLobbyOnline();
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

}
