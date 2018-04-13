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

  private static CreateNewAccountController newAccCon;
  private AnchorPane newAcc = null;

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

  private static CalValueController calValCon;
  private AnchorPane calVal = null;

  private static HelpAuctionController helpAucCon;
  private AnchorPane helpAuc = null;

  private static HelpCardsController helpCardsCon;
  private AnchorPane helpCards = null;

  private static HelpDealController helpDealCon;
  private AnchorPane helpDeal = null;

  private static HelpOverviewController helpOverCon;
  private AnchorPane helpOver = null;

  private static HelpPlayController helpPlayCon;
  private AnchorPane helpPlay = null;

  private static HelpPosController helpPosCon;
  private AnchorPane helpPos = null;

  private static HelpScoringController helpScorCon;
  private AnchorPane helpScor = null;

  private static HelpVariationsController helpVarCon;
  private AnchorPane helpVar;

  private static LeaderboardController leaderbordCon;
  private AnchorPane leaderbord;

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

  public void displayCreateNewAccount() {
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("CreateNewAccount.fxml"));
      this.newAcc = (AnchorPane) loader.load();
      mprimaryStage.getScene().setRoot(newAcc);

      newAccCon = loader.getController();
    } catch (IOException e) {
      // TODO Auto-generated catch block
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

  public void displayHelp_Overview() {
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("Overview.fxml"));
      this.helpOver = (AnchorPane) loader.load();
      mprimaryStage.getScene().setRoot(helpOver);

      helpOverCon = loader.getController();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public void displayHelp_Cards() {
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("TheCards.fxml"));
      this.helpCards = (AnchorPane) loader.load();
      mprimaryStage.getScene().setRoot(helpCards);

      helpCardsCon = loader.getController();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public void displayHelp_Deal() {
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("TheDeal.fxml"));
      this.helpDeal = (AnchorPane) loader.load();
      mprimaryStage.getScene().setRoot(helpDeal);

      helpDealCon = loader.getController();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public void displayHelp_Auction() {
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("TheAuction.fxml"));
      this.helpAuc = (AnchorPane) loader.load();
      mprimaryStage.getScene().setRoot(helpAuc);

      helpAucCon = loader.getController();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public void displayHelp_PossibleContracts() {
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("ThePossibleContracts.fxml"));
      this.helpPos = (AnchorPane) loader.load();
      mprimaryStage.getScene().setRoot(helpPos);

      helpPosCon = loader.getController();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public void displayHelp_Play() {
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("ThePlay.fxml"));
      this.helpPlay = (AnchorPane) loader.load();
      mprimaryStage.getScene().setRoot(helpPlay);

      helpPlayCon = loader.getController();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public void displayHelp_CalculateValue() {
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("CalValue.fxml"));
      this.calVal = (AnchorPane) loader.load();
      mprimaryStage.getScene().setRoot(calVal);

      calValCon = loader.getController();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public void displayHelp_Scoring() {
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("TheScoring.fxml"));
      this.helpScor = (AnchorPane) loader.load();
      mprimaryStage.getScene().setRoot(helpScor);

      helpScorCon = loader.getController();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public void displayHelp_Variations() {
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("Variations.fxml"));
      this.helpVar = (AnchorPane) loader.load();
      mprimaryStage.getScene().setRoot(helpVar);

      helpVarCon = loader.getController();
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
      Image jc = new Image(getClass().getResource("/Jhearts.jpg").toExternalForm());

      // InGameController con = new InGameController(jc, jc, jc, jc, jc, jc, jc, jc, jc, jc);
      // loader.setController(con);
      this.inGame = (AnchorPane) loader.load();
      mprimaryStage.getScene().setRoot(inGame);

      inGameCon = loader.getController();

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

  public void displayLeaderboard() {
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("Leaderboard.fxml"));
      this.leaderbord = (AnchorPane) loader.load();
      mprimaryStage.getScene().setRoot(leaderbord);

      leaderbordCon = loader.getController();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);

  }

}
