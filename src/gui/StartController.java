package gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class StartController {

	public MainClass main;
	@FXML
	private TextField nutzername;
	@FXML
	private Button neuanmelden;
	@FXML
	private AnchorPane root;
	@FXML
	private HBox hbox;

	@FXML
	public void login() {
		// Nutzernamen an Logik weitergeben
		// Spielerprofil laden
		Stage newStage = new Stage();
		try {
			root = (AnchorPane) FXMLLoader.load(getClass().getResource("Neuanmelden.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		newStage.setScene(scene);
		newStage.setTitle("Neuanmelden");
		newStage.showAndWait();
		System.exit(0);
	}

	@FXML
	public void neuerAccount() {
		// Settingsfeld für neuen Benutzernamen anzeigen
		nutzername.setPromptText("Neuer Nutzername");
		nutzername.setPrefHeight(71);
		hbox.getChildren().remove(neuanmelden);
	}

	public void setMain(MainClass main2) {
		// TODO Auto-generated method stub
		this.main = main2;

	}
}
