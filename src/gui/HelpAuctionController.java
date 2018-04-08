package gui;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;

public class HelpAuctionController {

	@FXML
	private JFXButton back;

	private GuiController main;

	public HelpAuctionController() {
		this.main = new GuiController();
	}

	@FXML
	public void back() {
		main.displayHelp();
	}

}
