package logic;

import java.util.Random;

public class Game implements GameInterface{
  private GameSettings gameSettings;
  private Player[] group; // gives us all the Players and the seating order
  private int pointerF; // supposed to always point on the Forehand
  private int[] gameScores; // for every player, same order as group
  
  public Game(Player[] group) {
    this.gameSettings = new GameSettings();
    this.group = group;
    this.defineSeatingList(group);
    this.setPointerF(0);  
    
    
  }
  
  public void setPointerF(int pointer) {
    
  }
  
  @Override
  public int checkNumberOfPlayers() {
    // TODO Auto-generated method stub
    return group.length;
  }
  
  
  @Override
  public void defineSeatingList(Player[] group) {
    // TODO Auto-generated method stub
    
    int randomIndex;
    Player temp;
    
    for (int i = 0; i < group.length - 1; i++) {
      randomIndex = (int)(Math.random() * (group.length));
      temp = group[i];
      group[i] = group[randomIndex];
      group[randomIndex] = temp;
    }
    this.group = group;
  }
  
  @Override
  public void setNumberOfPlays(int nr) {
    // TODO Auto-generated method stub
    
  }
  @Override
  public void startPLay() {
    // TODO Auto-generated method stub
    
  }

  
  //we need a method getPositin in here
  
  
  public static void main(String [] args) {
    
    //test
    Player anne = new Player("Anne");
    Player larissa = new Player("Larissa");
    Player felix = new Player("Felix");
    
    
    Player[] group = {anne, larissa, felix};
    Game game = new Game(group);
    
    for(int i=0; i<group.length; i++) {
      System.out.println(group[i].getName() + " ");
    }
    
    }
  }