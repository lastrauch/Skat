package logic;

import java.io.Serializable;

public enum Position implements Serializable {
  FOREHAND(1), MIDDLEHAND(2), REARHAND(3), DEALER(4);

  private Position(int i) {}

}
