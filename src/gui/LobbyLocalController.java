package gui;

import java.net.URL;
import java.util.ResourceBundle;
import interfaces.GuiLogic;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import logic.GameController;

public class LobbyLocalController implements Initializable {

  private GuiController main;
  private int bot;

  @FXML
  private MenuItem easy1, med1, dif1, easy2, med2, dif2, easy3, med3, dif3, dis3;
  @FXML
  MenuButton bot1, bot2, bot3;

  public LobbyLocalController() {
    this.main = new GuiController();
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
  public void play() {
    main.displayInGame();
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // TODO Auto-generated method stub
    buttonHandler();

  }

  public void buttonHandler() {
    easy1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        if (checkIfBotEnabled()) {
          GuiLogic guiL = new GameController();
          guiL.deleteBot("bot1");
        }
        bot1.setText("Easy");
      }
    });
    med1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        if (checkIfBotEnabled()) {
          GuiLogic guiL = new GameController();
          guiL.deleteBot("bot1");
        }
        bot1.setText("Medium");
      }
    });
    dif1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        if (checkIfBotEnabled()) {
          GuiLogic guiL = new GameController();
          guiL.deleteBot("bot1");
        }
        bot1.setText("Hard");
      }
    });
    easy2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        if (checkIfBotEnabled()) {
          GuiLogic guiL = new GameController();
          guiL.deleteBot("bot2");
        }
        bot2.setText("Easy");
      }
    });
    med2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        if (checkIfBotEnabled()) {
          GuiLogic guiL = new GameController();
          guiL.deleteBot("bot2");
        }
        bot2.setText("Medium");
      }
    });
    dif2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        if (checkIfBotEnabled()) {
          GuiLogic guiL = new GameController();
          guiL.deleteBot("hard2");
        }
        bot2.setText("Hard");
      }
    });
    easy3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        if (checkIfBotEnabled()) {
          GuiLogic guiL = new GameController();
          guiL.deleteBot("bot1");
        }
        bot3.setText("Easy");
      }
    });
    med3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        if (checkIfBotEnabled()) {
          GuiLogic guiL = new GameController();
          guiL.deleteBot("bot3");
        }
        bot3.setText("Medium");
      }
    });
    dif3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        if (checkIfBotEnabled()) {
          GuiLogic guiL = new GameController();
          guiL.deleteBot("bot3");
        }
        bot3.setText("Hard");
      }
    });
    dis3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        if (checkIfBotEnabled()) {
          GuiLogic guiL = new GameController();
          guiL.deleteBot("bot3");
        }
        bot3.setText("Disabled");
      }
    });
  }

  public void buttonListenerMenuItem() {
    bot1.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        bot = 1;
      }
    });
    bot2.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        bot = 2;
      }
    });
    bot3.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        bot = 3;
      }
    });
  }

  public boolean checkIfBotEnabled() {
    switch (bot) {
      case 1:
        if (bot1.getText() != null) {
          return true;
        }
      case 2:
        if (bot2.getText() != null) {
          return true;
        }
      case 3:
        if (!(bot2.getText().equals("Disabled"))) {
          return true;
        }
      default:
        return false;
    }
  }

}
