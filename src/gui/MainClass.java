package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainClass extends Application {

	private Stage primaryStage;
	private AnchorPane root;

	public Stage getStage() {
		return primaryStage;
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			mainWindow();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public AnchorPane getAnchorPane() {
		return root;
	}

	public void mainWindow() {
		try {
			FXMLLoader loader = new FXMLLoader(MainClass.class.getResource("Login.fxml"));
			root = loader.load();

			StartController startCon = new StartController();
			startCon.setMain(this);

			Scene scene = new Scene(root);
			// scene.getStylesheets().add(getClass().getResource("gui.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
