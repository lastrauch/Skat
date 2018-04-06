package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class LoginController {

	public GuiController main;
	@FXML
	private TextField nutzername;
	@FXML
	private Button neuanmelden;
	@FXML
	private AnchorPane root;
	@FXML
	private HBox hbox;
	@FXML
	private ImageView jclubs;
	@FXML
	private ImageView jdiamonds;
	@FXML
	private ImageView jhearts;
	@FXML
	private ImageView jspades;

	public LoginController() {
		this.main = new GuiController();
	}

	@FXML
	public void login() {
		// Nutzernamen an Logik weitergeben
		// Spielerprofil laden
		main.displayChooseGame();
	}

	@FXML
	public void neuerAccount() {
		// Settingsfeld f�r neuen Benutzernamen anzeigen
	}

	public void setMain(GuiController main2) {
		// TODO Auto-generated method stub
		this.main = main2;

	}
}
