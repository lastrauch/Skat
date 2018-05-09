package gui;

import java.io.IOException;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class GuiController extends Application {

  /**
   * 
   * Initializes all Controllers and their panes.
   * 
   * @author lstrauch
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

  private static Leaderboard3Controller leaderboard3Con;
  private AnchorPane leaderboard3;

  private static Leaderboard4Controller leaderboard4Con;
  private AnchorPane leaderboard4;



  protected static int prevScreen = 0;



  /**
   * starts the first screen (Login).
   * (non-Javadoc)
   * 
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
   * opens CreateNewAccount-screen.
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
   * opens ChooseGame-screen.
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
   * opens SetAi-screen.
   * @author lstrauch
   */
  public void displayAi() {
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
   * opens LobbyOnline-screen.
   * @author lstrauch
   */
  public void displayLobbyOnline() {
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("LobbyOnline.fxml"));
      Thread.sleep(1000);
      this.lobbyOnline = (AnchorPane) loader.load();
      mprimaryStage.getScene().setRoot(lobbyOnline);

      lobbyOnlineCon = loader.getController();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }


  /**
   * opens Lobby-screen.
   * @author lstrauch
   */
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
   * opens AccountSettings-screen.
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
   * opens Help-screen.
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
   * opens Overiew-screen.
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
   * opens TheCards-screen.
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
   * opens TheDeal-screen.
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
   * opens TheAuction-screen.
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
   * opens ThePossibleContracts-screen.
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
   * opens ThePlay-screen.
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
   * opens CalValue-screen.
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
   * opens TheScoring-screen.
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
   * opens Varations-screen.
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
   * opens GameSettings-screen.
   * @author lstrauch
   * @param gm
   */
  public void displayGameSettings() {
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("GameSettings.fxml"));
      this.gameSettings = (AnchorPane) loader.load();
      mprimaryStage.getScene().setRoot(gameSettings);

      gameSettingsCon = loader.getController();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }


  /**
   * opens InGame-screen.
   * @author lstrauch
   */
  public void displayInGame() {
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("InGame.fxml"));
      inGame = (AnchorPane) loader.load();
      Thread.sleep(1000);
      mprimaryStage.getScene().setRoot(inGame);
      inGameCon = loader.getController();
      Thread.sleep(1000);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }


  }

  /**
   * opens Settings-screen.
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
   * opens Leaderboard3-screen.
   * @author lstrauch
   */
  public void displayLeaderboard3() {
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("Leaderboard3.fxml"));
      this.leaderboard3 = (AnchorPane) loader.load();
      Thread.sleep(1000);
      mprimaryStage.getScene().setRoot(leaderboard3);

      leaderboard3Con = loader.getController();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * opens Leaderboard4-screen.
   * @author lstrauch
   */
  public void displayLeaderboard4() {
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("Leaderboard4.fxml"));
      leaderboard4 = (AnchorPane) loader.load();
      Thread.sleep(1000);
      mprimaryStage.getScene().setRoot(leaderboard4);

      leaderboard4Con = loader.getController();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * returns InGameController.
   * @author lstrauch
   * @return inGameCon
   */
  public InGameController getCon() {
    return inGameCon;
  }

  /**
   * returns GameSettingsController.
   * @author lstrauch
   * @return gameSettingsCon;
   */
  public GameSettingsController getGameSetCon() {
    return gameSettingsCon;
  }

  /**
   * returns LobbyController.
   * @author lstrauch
   * @return lobbyCon
   */
  public LobbyController getLobbyCon() {
    return lobbyCon;
  }

  /**
   * returns ChooseGameController.
   * @author lstrauch
   * @return gameModeCon
   */
  public ChooseGameController getChooseGameCon() {
    return gameModeCon;
  }

  /**
   * returns InGameController.
   * @author lstrauch
   * @return inGameCon
   */
  public InGameController getInGameCon() {
    return inGameCon;
  }

  /**
   * returns Leaderboard4Controller
   * @author lstrauch
   * @return leaderboard4Con
   */
  public Leaderboard4Controller getLead4Con() {
    return leaderboard4Con;
  }
  
  /**
   * returns SettingsCon.
   * @author lstrauch
   * @return settingsCon
   */
  public SettingsController getSettingsCon() {
    return settingsCon;
  }


}


