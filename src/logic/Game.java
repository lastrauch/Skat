package logic;

import java.util.Random;

public class Game implements GameInterface {
  private GameSettings gameSettings;
  private Player[] group; // gives us all the Players and the seating order
  private int pointerF; // supposed to always point on the Forehand
  private int[] gameScores; // for every player, same order as group

  public Game(Player[] group) {
    try {
      this.gameSettings = new GameSettings(CountRule.NORMAL, group.length, 3);
    } catch (LogicException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    this.initializeGroupSettings(group);
  }

  public Game(Player[] group, CountRule countRule, int numberOfPlays) {
    try {
      this.gameSettings = new GameSettings(countRule, group.length, numberOfPlays);
    } catch (LogicException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    this.initializeGroupSettings(group);
  }

  public void initializeGroupSettings(Player[] group) {
    this.group = group;
    this.defineSeatingList(group);
    this.setPointerF(0);
    this.updatePosition();
    this.initializeGameScores();
  }

  public void initializeGameScores() {7
    this.gameScores = new int[this.group.length];
    for (int i = 0; i < this.group.length; i++) {    
      this.gameScores[i] = 0;
    }
  }

  @Override
  public void defineSeatingList(Player[] group) {
    // TODO Auto-generated method stub

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



  public void setPointerF(int pointer) {
    this.pointerF = pointer;
  }

 
  @Override
  public void startPLay() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void updatePosition() {
    // TODO Auto-generated method stub
    this.group[this.pointerF].setPosition(Position.FOREHAND);
    this.group[((this.pointerF + 1) % this.group.length)].setPosition(Position.MIDDLEHAND);
    this.group[((this.pointerF + 2) % this.group.length)].setPosition(Position.REARHAND);


    if (group.length == 4) {
      this.group[((this.pointerF + 3) % this.group.length)].setPosition(Position.DEALER);
    }
  }

  @Override
  public void setGameScore(int index, int addThis) {
    // TODO Auto-generated method stub
    this.gameScores[index] += addThis;
  }

  public static void main(String[] args) {

    // test
    Player anne = new Player("Anne");
    Player larissa = new Player("Larissa");
    Player felix = new Player("Felix");


    Player[] group = {anne, larissa, felix};
    Game game = new Game(group);

    for (int i = 0; i < group.length; i++) {
      System.out.println(group[i].getName() + " ");
    }

  }



}
