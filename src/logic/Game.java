package logic;

import java.util.Random;
import gui.ImplementsLogicGui;
import interfaces.InGameInterface;
import interfaces.LogicGui;
import interfaces.LogicNetwork;

public class Game {
  private GameSettings gameSettings;
  private Player[] group; // gives us all the Players and the seating order
  private int pointerF; // supposed to always point on the Forehand
  private int playerFirstCard; // switch every in every play --> depends on auction
  private Play[] plays;
  private int currentPlay;
  private Card[] cards;
  private Player winner;
  private GameMode gameMode; // singlePlayer or Multiplayer
  private LogicNetwork logicNetwork;


  /**
   * defines group settings for the start, some settings have to be updated during the game *
   * 
   * @author sandfisc
   * @param group
   */
  public void initializeGroupSettings(Player[] group) {
    this.group = group;
    this.setPointerF(0);
    this.updatePosition();
    this.gameSettings.setNrOfPlayers(group.length);
    
    // set all points of the players 0
    for (int i = 0; i < this.group.length; i++) {
      this.group[i].setGamePoints(0);
    }
  }


  /**
   * initializes the cards
   * 
   * @author awesch
   */
  public void initializeCards() {
    this.cards = new Card[32];

    int counter = 0;
    for (int i = 1; i <= 4; i++) {
      Colour col = null;
      switch (i) {
        case 1:
          col = Colour.DIAMONDS;
          break;
        case 2:
          col = Colour.HEARTS;
          break;
        case 3:
          col = Colour.SPADES;
          break;
        case 4:
          col = Colour.CLUBS;
          break;
      }
      for (int j = 1; j <= 8; j++) {
        Number nr = null;
        switch (j) {
          case 1:
            nr = Number.SEVEN;
            break;
          case 2:
            nr = Number.EIGHT;
            break;
          case 3:
            nr = Number.NINE;
            break;
          case 4:
            nr = Number.JACK;
            break;
          case 5:
            nr = Number.QUEEN;
            break;
          case 6:
            nr = Number.KING;
            break;
          case 7:
            nr = Number.TEN;
            break;
          case 8:
            nr = Number.ASS;
            break;
        }
        // cards are generated in the order of their value

        Card c = new Card(col, nr);
        cards[counter] = c;
        counter++;

        // System.out.println(counter + " " + col.toString() + " " + nr.toString());
      }
    }
  }



  /**
   * here is where the magic/game happens
   * 
   * @author sandfisc
   */
  public void runGame(Player[] group) {

    this.initializeGroupSettings(group);
    this.initializeCards();
    
    boolean breakPlease = false; // is set true when the game is over before all plays are played
                                 // #BIERLACHS
    Player[] playingGroup = new Player[3]; // always three players who are actually playing

    // if only three players play then the playing group is the whole group "at the table"
    if (this.group.length == 3) {
      playingGroup = this.group;
    }  

    // ask for gameSettings
   // this.askForGameSettings();

    for (int i = 0; i < this.plays.length; i++) {
      this.currentPlay ++;
      
      // the playing group consists of forehand, middlehand, rarehand, NOT dealer
      if (this.group.length == 4) {
        int index = 0;
        for (int j = 0; j < this.group.length; j++) {
          if (this.group[j].getPosition() != Position.DEALER) {
            playingGroup[index] = this.group[j];
            index++;
          }
        }
      }

      try {
        // play one play with a sorted (playing) group
        this.plays[i] = new Play(this.sortPlayingGroup(playingGroup), gameSettings, this.cards);
        this.plays[i].runPlay();
      } catch (LogicException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

      // when a player reaches the endPointsBierlachs the game is over
      if (this.gameSettings.getCountRule() == CountRule.BIERLACHS) {
        for (int j = 0; j < this.group.length; j++) {
          if (this.group[j].getGamePoints() <= this.gameSettings.getEndPointsBierlachs()) {
            breakPlease = true;
          }
        }
      }
      // #BIERLACHS
      if (breakPlease) {
        this.calculateWinner();
        break;
      }
      
      //test
//      for (int j = 0; j < this.group.length; j++) {
//        System.out.println(group[j].getName() + "'s GamePoints: " + group[j].getGamePoints());
//      }

      // after a play the players change positions
      this.setPointerF((i + 1) % group.length);
      this.updatePosition();
    }
    // when the game is over the winner is calculated
    this.calculateWinner();
  }

//  /**
//   * only the host is asked to set the gameSettings
//   * 
//   * @author sandfisc
//   */
//  public void askForGameSettings() {
//    for (int i = 0; i < this.group.length; i++) {
//      if (this.group[i].isHost()) {
//        // this.group[i].implementsLogicGui.setGameSettings(this.gameSettings);
//        this.group[i].logicGui.setGameSettings(this.gameSettings);
//      }
//    }
//  }

  /**
   * sorts a group: index 0 = forehand, index 1 = middlehand, index 2 = rarehand
   * 
   * @author sandfisc
   * @param group
   * @return sorted group
   * @throws LogicException
   */
  public Player[] sortPlayingGroup(Player[] group) throws LogicException {
    Player[] sortedGroup = new Player[group.length];

    for (int i = 0; i < group.length; i++) {
      if (group[i].getPosition() == Position.FOREHAND) {
        sortedGroup[0] = group[i];
      } else if (group[i].getPosition() == Position.MIDDLEHAND) {
        sortedGroup[1] = group[i];
      } else if (group[i].getPosition() == Position.REARHAND) {
        sortedGroup[2] = group[i];
      } else {
        throw new LogicException("sorting the group is not possible");
      }
    }

    return sortedGroup;
  }

  /**
   * setter: pointer on forehand
   * 
   * @author sandfisc
   */
  public void setPointerF(int pointer) {
    this.pointerF = pointer;
  }

  /**
   * sets the index of the player who plays the first card in the next game
   * 
   * @author sandfisc
   * @param index
   */
  public void setPlayerFirstCard(int index) {
    this.playerFirstCard = index;
  }

  public void setGroup(Player[] group) {
    this.group = group;
  }
  
  public Player[] getGroup() {
    return this.group;
  }

  /**
   * position (forehand, middlehand, rearhand) changes ater every play
   * 
   * @author sandfisc
   */
  public void updatePosition() {
    this.group[this.pointerF].setPosition(Position.FOREHAND);
    this.group[((this.pointerF + 1) % this.group.length)].setPosition(Position.MIDDLEHAND);
    this.group[((this.pointerF + 2) % this.group.length)].setPosition(Position.REARHAND);


    if (group.length == 4) {
      this.group[((this.pointerF + 3) % this.group.length)].setPosition(Position.DEALER);
    }
  }

  /**
   * calculates the winner of the whole game
   * 
   * @author sandfisc
   */
  public Player calculateWinner(PlayState ps) {
    Player winner = ps.getGroup()[0];
    for (int i = 1; i < ps.getGroup().length; i++) {
      if (ps.getGroup()[i].getGamePoints() > winner.getGamePoints()) {
        this.winner = ps.getGroup()[i];
      }
    }
    return winner;
  }

  public void setGameSettings(GameSettings gameSettings) {
    this.gameSettings = gameSettings;
  }

  public GameSettings getGameSettings() {
    return this.gameSettings;
  }
  
  public void setPlays(Play[] plays) {
    this.plays = plays;
  }
  
  public Play[] getPlays() {
    return this.plays;
  }
  
  public Play getCurrentPlay() {
    return this.plays[this.currentPlay];
  }
}
