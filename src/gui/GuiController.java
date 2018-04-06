package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GuiController extends Application {

	private Stage primaryStage;
	private AnchorPane root;
	@FXML
	private ImageView jclubs;
	@FXML
	private ImageView jdiamonds;
	@FXML
	private ImageView jhearts;
	@FXML
	private ImageView jspades;
	@FXML
	private AnchorPane ap;
	@FXML
	private ImageView profilepic;

	@FXML
	private ImageView c1;

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

	public void displayChooseGame() {
		Stage window = new Stage();

		try {
			root = (AnchorPane) FXMLLoader.load(getClass().getResource("ChooseGame.fxml"));
			Image jcc = new Image(getClass().getResource("/Jclubs.png").toExternalForm());

			profilepic = new ImageView(jcc);
			profilepic.setFitHeight(65);
			profilepic.setFitWidth(65);
			profilepic.setLayoutX(895);
			profilepic.setLayoutY(196.5);
			root.getChildren().add(profilepic);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Scene scene = new Scene(root);
		window.setScene(scene);
		window.showAndWait();
		System.exit(0);
	}

	public void displayLobbyLocal() {
		Stage window = new Stage();
		try {
			root = (AnchorPane) FXMLLoader.load(getClass().getResource("LobbyLocal.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		window.setScene(scene);
		window.showAndWait();
		System.exit(0);
	}

	public void displayLobbyOn() {
		Stage window = new Stage();
		try {
			root = (AnchorPane) FXMLLoader.load(getClass().getResource("LobbyOnline.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		window.setScene(scene);
		window.showAndWait();
		System.exit(0);
	}

	public void displayInGame() {
		// Layout Links:
		// Alle:
		// Fith Width: 200
		// Fit Hight: 150
		// -1.: Layout X: -66; Layout Y: 4
		// -2.: Layout X: -66; LayoutY: 33
		// -3.,4.,5.,...: Layout X:-66; Layout Y: y+29;

		// Layout rechts:
		// -Layout X: 1214; Layout Y: 4
		// Layout X: 1214; Layout Y: y+29;

		// Layout eigene:
		// Fit Widht: 182; Fit Hight; 227
		// 125:527:-17.4; 225:504:-11.3; 325:489:-8.1; 425:474:-6.3;
		// 525:470:-3.2; 625: 470:3.2; 725: 474:6.3; 825:
		// 489:8.1; 925:504:11.3; 1025:530:17.4;

		// Skatblatt links:
		// FitWidth: 200; Fit Hight: 150
		// Layout X: 6; Layout Y: 125

		// Skatblatt rechts:
		// 1136: 125

		// Skatblatt Mitte:
		// 557: 377

		// Skatblatt:
		// Größe: 132: 205
		// Layout: 584:9:Rotation -6.3; 618:14:Rotation 0

		// Stichblatt:
		// Unten: Größe: 132:205; Layout: 619:60; Rotation: 91.7
		// Mitte: Größe: 132:205; Layout: 590:44; Rotation: 14.9
		// Unten: Größe: 132:205; Layout: 553:59; Rotation: 125

		Stage window = new Stage();
		try {
			root = (AnchorPane) FXMLLoader.load(getClass().getResource("InGame.fxml"));
			Image jcc = new Image(getClass().getResource("/Jclubs.png").toExternalForm());

			c1 = new ImageView(jcc);
			c1.setFitHeight(227);
			c1.setFitWidth(182);
			c1.setLayoutX(125);
			c1.setLayoutY(527);
			c1.setRotate(-17.4);
			c1.setId("c1");

			root.getChildren().add(c1);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		window.setScene(scene);
		window.showAndWait();
		System.exit(0);

	}

	public AnchorPane getAnchorPane() {
		return root;
	}

	public void mainWindow() {
		try {
			FXMLLoader loader = new FXMLLoader(GuiController.class.getResource("Login.fxml"));
			root = loader.load();

			LoginController startCon = new LoginController();
			startCon.setMain(this);

			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ImageView getC1() {
		return c1;
	}

	public AnchorPane getPane() {
		return root;
	}

	public static void main(String[] args) {
		launch(args);

	}

}
