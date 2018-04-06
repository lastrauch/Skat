package database;

import java.sql.Blob;

public class Player {
  
  private int id, score;
  private String name, nickname;
  private Blob profilePicture;
  

  public Player(int id, String name, String nickname, int score, Blob profilePicture) {
      this.id = id;
      this.name = name;
      this.nickname = nickname;
      this.score = score;
      this.profilePicture = profilePicture;
      
  }
  public int getId(){
    return id;
}

public String getName() {
    return name;
}

public String getNickname() {
    return nickname;
}

public int getScore() {
    return score;
}

public Blob getProfilePicture() {
    return profilePicture;
}

public String toString() {
  return "Spieler: " + name + ", " + nickname + ", " + score + ", "  + profilePicture;
  
}

}
