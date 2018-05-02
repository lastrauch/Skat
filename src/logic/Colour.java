package logic;

import java.io.Serializable;

public enum Colour implements Serializable{
  DIAMONDS(1), HEARTS(2), SPADES(3), CLUBS(4); // order in german: Karo, Herz, Pik, Kreuz
  private int value;

  private Colour(int i) {
    switch (i) {
      case 1:
        this.value = 1;
        break;
      case 2:
        this.value = 2;
        break;
      case 3:
        this.value = 3;
        break;
      case 4:
        this.value = 4;
        break;
    }
  }

  public int compareColourIntern(Colour comp) {
    if (this.value == comp.value) {
      return 0;
    } else if (this.value < comp.value) {
      return -1;

    } else {
      return 1;
    }
  }

}
