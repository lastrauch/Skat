package database;

import java.sql.Blob;

public class Cards {
  private int value, id;
  private String type;
  private Blob image;

  public Cards(int id, int value, Blob image, String type) {
      this.value = value;
      this.image = image;
      this.type = type;
  }
  public int getId() {
    return id;
  }

public String getType() {
    return type;
}

public int getValue() {
    return value;
}

public Blob getImage() {
    return image;
}
public String toString() {
  return value + " "+image+", " + type;
}

}



