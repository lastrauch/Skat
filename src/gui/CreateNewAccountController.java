package gui;

import javafx.fxml.FXML;

public class CreateNewAccountController {

	private GuiController main;

	public CreateNewAccountController() {
		this.main = new GuiController();
	}

	@FXML
	public void back() {
		main.displayChooseGame();
	}

}
