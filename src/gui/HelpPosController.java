package gui;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;

public class HelpPosController {

	@FXML
	private JFXButton back1;
	@FXML
	private JFXButton back2;
	@FXML
	private JFXButton back3;
	@FXML
	private JFXButton back4;

	private GuiController main;

	public HelpPosController() {
		this.main = new GuiController();
	}

	@FXML
	public void back() {
		main.displayHelp();
	}

}
