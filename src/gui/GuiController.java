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
import logic.GameMode;

public class GuiController extends Application {

  /**
   * @author lstrauch
   * 
   * 
   * 
   * 
   */
  
  static Stage mprimaryStage;
  private AnchorPane root;

  private static InGameController inGameCon;
  private AnchorPane inGame = null;

  private static LoginController loginCon;
  private AnchorPane login = null;

  static CreateNewAccountController newAccCon;
  private AnchorPane newAcc = null;

  private static ChooseGameController gameModeCon;
  private AnchorPane gameMode = null;
  
  private static LobbyController lobbyCon;
  private AnchorPane lobby = null;

  private static SetAIController lobbyLocalCon;
  private AnchorPane lobbyLocal = null;

  private static LobbyOnlineController lobbyOnlineCon;
  private AnchorPane lobbyOnline = null;

  private static AccountSettingsController accountSettingsCon;
  private AnchorPane accountSettings = null;

  private static GameSettingsController gameSettingsCon;
  private AnchorPane gameSettings = null;


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
  
  
  
  
  protected static int prevScreen = 0;



  /** (non-Javadoc)
   * @see javafx.application.Application#start(javafx.stage.Stage)
   * 
   * @author lstrauch
   */
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

  /**
   * @author lstrauch
   */
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

  /**
   * @author lstrauch
   */
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

  /**
   * @author lstrauch
   */
  public void displayAI() {
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("SetAI.fxml"));
      this.lobbyLocal = (AnchorPane) loader.load();
      mprimaryStage.getScene().setRoot(lobbyLocal);

      lobbyLocalCon = loader.getController();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * @author lstrauch
   */
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
  
  public void displayLobby() {
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("Lobby.fxml"));
      this.lobby = (AnchorPane) loader.load();
      mprimaryStage.getScene().setRoot(lobby);

      lobbyCon = loader.getController();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * @author lstrauch
   */
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


  /**
   * @author lstrauch
   */
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

  /**
   * @author lstrauch
   */
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

  /**
   * @author lstrauch
   */
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

  /**
   * @author lstrauch
   */
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

  /**
   * @author lstrauch
   */
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

  /**
   * @author lstrauch
   */
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

  /**
   * @author lstrauch
   */
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

  /**
   * @author lstrauch
   */
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

  /**
   * @author lstrauch
   */
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

  /**
   * @author lstrauch
   */
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

  /**
   * @author lstrauch
   * @param gm
   */
  public void displayGameSettings(GameMode gm) {
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("GameSettings.fxml"));
      this.gameSettings = (AnchorPane) loader.load();
      mprimaryStage.getScene().setRoot(gameSettings);

      gameSettingsCon = loader.getController();
      gameSettingsCon.setMode(gm);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }


  /**
   * @author lstrauch
   */
  public void displayInGame() {

    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("InGame.fxml"));

      this.inGame = (AnchorPane) loader.load();
      mprimaryStage.getScene().setRoot(inGame);

      inGameCon = loader.getController();

    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  /**
   * @author lstrauch
   */
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

  /**
   * @author lstrauch
   */
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
  

}
