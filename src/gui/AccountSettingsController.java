package gui;

import javafx.fxml.FXML;

public class AccountSettingsController {

	private GuiController main;

	public AccountSettingsController() {
		this.main = new GuiController();
	}

	@FXML
	public void back() {
		main.displayChooseGame();
	}

}
