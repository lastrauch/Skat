package gui;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ChooseGameController {

	@FXML
	private GuiController main;
	@FXML
	private ImageView profilepic;

	public ChooseGameController() {
		this.main = new GuiController();

		// setProfilePicture();
	}

	@FXML
	public void SinglePlayer() {
		main.displayLobbyLocal();
	}

	@FXML
	public void MultiPlayer() {
		main.displayLobbyOn();
	}

	public void setProfilePicture() {
		Image jc = new Image(getClass().getResource("/Jclubs.png").toExternalForm());
		profilepic.setImage(jc);
	}
}
