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

	private static Stage mprimaryStage;
	private AnchorPane root;

	private static InGameController inGameCon;
	private AnchorPane inGame = null;

	private static LoginController loginCon;
	private AnchorPane login = null;

	private static ChooseGameController gameModeCon;
	private AnchorPane gameMode = null;

	private static LobbyLocalController lobbyLocalCon;
	private AnchorPane lobbyLocal = null;

	private static LobbyOnlineController lobbyOnlineCon;
	private AnchorPane lobbyOnline = null;

	private static AccountSettingsController accountSettingsCon;
	private AnchorPane accountSettings = null;

	private static BettingController bettingCon;
	private AnchorPane betting = null;

	private static HelpController helpCon;
	private AnchorPane help = null;

	private static SettingsController settingsCon;
	private AnchorPane settings = null;

	@FXML
	private ImageView c1;

	@Override
	public void start(Stage primaryStage) {
		mprimaryStage = primaryStage;
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Login.fxml"));
			login = (AnchorPane) loader.load();

			Scene loginScene = new Scene(login);

			loginCon = loader.getController();

			mprimaryStage.setScene(loginScene);
			mprimaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void displayChooseGame() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("GameMode.fxml"));
			this.gameMode = (AnchorPane) loader.load();
			mprimaryStage.getScene().setRoot(gameMode);

			gameModeCon = loader.getController();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void displayLobbyLocal() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("LobbyLocal.fxml"));
			this.lobbyLocal = (AnchorPane) loader.load();
			mprimaryStage.getScene().setRoot(lobbyLocal);

			lobbyLocalCon = loader.getController();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void displayLobbyOnline() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("LobbyOnline.fxml"));
			this.lobbyOnline = (AnchorPane) loader.load();
			mprimaryStage.getScene().setRoot(lobbyOnline);

			lobbyOnlineCon = loader.getController();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void displayAccountSettings() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("AccountSettings.fxml"));
			this.settings = (AnchorPane) loader.load();
			mprimaryStage.getScene().setRoot(settings);

			accountSettingsCon = loader.getController();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void displayBetting() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Betting.fxml"));
			this.betting = (AnchorPane) loader.load();
			mprimaryStage.getScene().setRoot(betting);

			bettingCon = loader.getController();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void displayHelp() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Help.fxml"));
			this.help = (AnchorPane) loader.load();
			mprimaryStage.getScene().setRoot(help);

			helpCon = loader.getController();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("InGame.fxml"));

			InGameController con = new InGameController(
					new Image(getClass().getResource("/Jhearts.jpg").toExternalForm()));
			loader.setController(con);
			this.inGame = (AnchorPane) loader.load();
			mprimaryStage.getScene().setRoot(inGame);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void displaySettings() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Settings.fxml"));
			this.settings = (AnchorPane) loader.load();
			mprimaryStage.getScene().setRoot(settings);

			settingsCon = loader.getController();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);

	}

}
