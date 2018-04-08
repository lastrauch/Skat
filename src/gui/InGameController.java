package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class InGameController implements Initializable {

	private ImageView card1Id = new ImageView();

	@FXML
	private ImageView c1;

	public InGameController(Image cc1) {
		card1Id.setImage(cc1);
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		c1.setImage(card1Id.getImage());

	}

}
