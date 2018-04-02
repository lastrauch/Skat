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


  /**
   * constructor #1
   * 
   * @param group
   */
  public Game(Player[] group) {
    try {
      this.gameSettings = new GameSettings(CountRule.NORMAL, group.length, 3);
    } catch (LogicException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    this.plays = new Play[3];
    this.auctions = new Auction[3];
    this.initializeGroupSettings(group);
    this.organizeGame();
  }

  /**
   * constructor #2
   * 
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
    this.auctions = new Auction[nrOfPlays];
    this.initializeGroupSettings(group);
    this.organizeGame();
  }

  /**
   * defines group settings for the start, some settings have to be updated during the game
   * 
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
   */
  public void initializeGameScores() {
    this.gameScores = new int[this.group.length];
    for (int i = 0; i < this.group.length; i++) {
      this.gameScores[i] = 0;
    }
  }


  /**
   * defines in which order players "sitting on a table" (random)
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
   */
  //#kackhaufen
  public void organizeGame() {
    for (int i = 0; i < this.plays.length; i++) {
      this.auctions[i] = new Auction(this.group); //we need a change here.. group means sometimes 4 people, but we need 3
      this.plays[i] = new Play(this.group);
    }
  }

  /**
   * setter: pointer on forehand
   */
  public void setPointerF(int pointer) {
    this.pointerF = pointer;
  }

  /**
   * @param index: is ths idex of the player whose score has to be updated
   * @param addThis: points (goals of the play)
   */
  public void setGameScore(int index, int addThis) {
    this.gameScores[index] += addThis;
  }

  /**
   * sets the index of the player who plays the first card in the next game
   * @param index
   */
  public void setPlayerFirstCard(int index) {
    this.playerFirstCard = index;
  }
  
  /**
   * position (forehand, middlehand, rearhand) changes ater every play
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

    // // test
    // Player anne = new Player("Anne");
    // Player larissa = new Player("Larissa");
    // Player felix = new Player("Felix");
    //
    //
    // Player[] group = {anne, larissa, felix};
    // Game game = new Game(group);
    //
    // for (int i = 0; i < group.length; i++) {
    // System.out.println(group[i].getName() + " ");
    // }

  }



}
