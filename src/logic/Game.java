package logic;

import java.util.Random;

public class Game {
  private GameSettings gameSettings;
  private Player[] group; // gives us all the Players and the seating order
  private int pointerF; // supposed to always point on the Forehand
  private int[] gameScores; // for every player, same order as group
  private int playerFirstCard; // switch every in every play --> depends on auction
  private Play[] plays;
  private Auction[] auctions;
  private int[] lostGames;
  private int[] wonGames;

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
    this.runGame();
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
    this.runGame();
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
    this.initializeGameScores();
  }

  /**
   * the scores of all players are initialized with 0 in the beginning
   * 
   * @author sandfisc
   */
  public void initializeGameScores() {
    this.gameScores = new int[this.group.length];
    for (int i = 0; i < this.group.length; i++) {
      this.gameScores[i] = 0;
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
   * here is where the magic/game happens
   * 
   * @author sandfisc
   */
  public void runGame() {
    Player[] playingGroup = new Player[3]; // always three players who are actually playing

    // if only three players then the playing group is the whole group "at the table"
    if (this.group.length == 3) {
      playingGroup = this.group;
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

      // //test: positions
      // for (int k = 0; k < this.group.length; k++) {
      // System.out.println(this.group[k].getName() + ": " + this.group[k].getPosition());
      // }
      // System.out.println("playing group:");
      // for (int k = 0; k < playingGroup.length; k++) {
      // System.out.println(playingGroup[k].getName());
      // }
      //
      try {
        this.plays[i] = new Play(this.sortPlayingGroup(playingGroup));
        this.plays[i].setGameSettings(this.gameSettings);
      } catch (LogicException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

      this.setPointerF((i + 1) % group.length);
      this.updatePosition();
    }
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
   * setter: pointer on forehand
   * 
   * @author sandfisc
   */
  public void setPointerF(int pointer) {
    this.pointerF = pointer;
  }

  /**
   * @author sandfisc
   * @param index: is the index of the player whose score has to be updated
   * @param addThis: points (goals of the play)
   */
  public void setGameScore(int index, int addThis) {
    this.gameScores[index] += addThis;
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


  public int[] getLostGames() {
    return this.lostGames;
  }

  public void setLostGames(int[] lostGames) {
    this.lostGames = lostGames;
  }

  public int[] getWonGames() {
    return this.lostGames;
  }

  public void setWonGames(int[] lostGames) {
    this.lostGames = lostGames;
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
  

  public static void main(String[] args) {

    // test
    Player anne = new Player("Anne");
    Player larissa = new Player("Larissa");
    Player felix = new Player("Felix");
    Player duygu = new Player("Duygu");

    Player[] group = {anne, larissa, felix, duygu};
    Game game = new Game(group);

    // test: define seatingList
    // for (int i = 0; i < group.length; i++) {
    // System.out.println(group[i].getName() + " ");
    // }

  }



}
