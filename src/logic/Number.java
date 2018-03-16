package logic;

public enum Number {
  SEVEN(1), EIGHT(2), NINE(3), JACK(4), QUEEN(5), KING(6), TEN(7), ASS(8);
  private int value;

  private Number(int i) {
    switch (i) {
      case 1:
        this.value = 0;
        break;
      case 2:
        this.value = 0;
        break;
      case 3:
        this.value = 0;
        break;
      case 4:
        this.value = 2;
        break;
      case 5:
        this.value = 3;
        break;
      case 6:
        this.value = 4;
        break;
      case 7:
        this.value = 10;
        break;
      case 8:
        this.value = 11;
        break;
    }
  }

  public int getValue() {
    return this.value;
  }
}
