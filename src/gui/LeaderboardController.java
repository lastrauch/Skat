package gui;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;

public class LeaderboardController {

	@FXML
	private JFXButton back;

	private GuiController main;

	public LeaderboardController() {
		this.main = new GuiController();
	}

	@FXML
	public void back() {
		main.displayChooseGame();
	}

}
