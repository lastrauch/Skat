package logic;

import java.io.Serializable;

public enum PlayMode implements Serializable {
  SUIT(1), GRAND(2), NULL(3);

  private PlayMode(int i) {}

}
