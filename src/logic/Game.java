package logic;

import java.util.Random;

public class Game {
  private GameSettings gameSettings;
  private Player[] group; // gives us all the Players and the seating order
  private int pointerF; // supposed to always point on the Forehand
  private int playerFirstCard; // switch every in every play
  private Play[] plays;
  private Card[] cards;
  private Player winner;

  /**
   * constructor #1
   * 
   * @author sandfisc
   * @param group
   */
  public Game(Player[] group) {
    this.gameSettings = new GameSettings();
    this.plays = new Play[this.gameSettings.getNrOfPlays()];
    this.initializeGroupSettings(group);
    this.initializeCards();
  }

  /**
   * constructor #2
   * 
   * @author sandfisc
   * @param group
   * @param countRule
   * @param nrOfPlays
   */
  public Game(Player[] group, CountRule countRule, int nrOfPlays) {
    try {
      this.gameSettings = new GameSettings(countRule, group.length, nrOfPlays);
    } catch (LogicException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    this.plays = new Play[nrOfPlays];
    this.initializeGroupSettings(group);
    this.initializeCards();
  }

  /**
   * defines group settings for the start, some settings have to be updated during the game *
   * 
   * @author sandfisc
   * @param group
   */
  public void initializeGroupSettings(Player[] group) {
    this.group = group;
    this.defineSeatingList(group);
    this.setPointerF(0);
    this.updatePosition();
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
   * defines in which order players "sitting on a table" (random)
   * 
   * @author sandfisc
   */
  public void defineSeatingList(Player[] group) {
    int randomIndex;
    Player temp;

    for (int i = 0; i < group.length - 1; i++) {
      randomIndex = (int) (Math.random() * (group.length));
      temp = group[i];
      group[i] = group[randomIndex];
      group[randomIndex] = temp;
    }
    this.group = group;
  }

  /**
   * all plays are startet in this methode and the winner is calculated
   * 
   * @author sandfisc
   */
  public void runGame() {

    boolean breakPlease = false; // is set true when the game is over before all plays are played
                                 // #BIERLACHS
    Player[] playingGroup = new Player[3]; // always three players who are actually playing

    // if only three players then the playing group is the whole group "at the table"
    if (this.group.length == 3) {
      playingGroup = this.group;
    }

    // set all points of the players 0
    for (int i = 0; i < this.group.length; i++) {
      this.group[i].setGamePoints(0);
    }

    for (int i = 0; i < this.plays.length; i++) {

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

      // test
      for(int j = 0; j < this.group.length; j++) {
        System.out.println(group[j].getName() + "'s GamePoints: " + group[j].getGamePoints());
      }
      
      // after a play the players change positions
      this.setPointerF((i + 1) % group.length);
      this.updatePosition();
    }
    // when the game is over the winner is calculated
    this.calculateWinner();
  }

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
  public void calculateWinner() {
    this.winner = this.group[0];
    for (int i = 1; i < this.group.length; i++) {
      if (group[i].getGamePoints() > this.winner.getGamePoints()) {
        this.winner = this.group[i];
      }
    }
    System.out.println("...and the winner is: " + this.winner.getName());
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

  public static void main(String[] args) {
    // test
    Player anne = new Player("Anne");
    Player larissa = new Player("Larissa");
    Player felix = new Player("Felix");
  //  Player duygu = new Player("Duygu");

    Player[] group = {anne, larissa, felix};
    Game game = new Game(group);
    game.runGame();
    // test: define seatingList
    // for (int i = 0; i < group.length; i++) {
    // System.out.println(group[i].getName() + " ");
    // }

  }
}

