package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class InGameController implements Initializable {

	private GuiController main;

	private ImageView card1Id = new ImageView();

	@FXML
	private ImageView c1;

	public InGameController(Image cc1) {
		card1Id.setImage(cc1);
		this.main = new GuiController();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		c1.setImage(card1Id.getImage());

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
	public void back() {
		main.displayChooseGame();
	}

}
