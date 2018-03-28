package logic;

public enum Number {
  SEVEN(1), EIGHT(2), NINE(3), JACK(4), QUEEN(5), KING(6), TEN(7), ASS(8);
  private int value;
  private int rankingNorm;
  private int rankingHighTen;

  private Number(int i) {
    switch (i) {
      case 1:
        this.value = 0;
        this.rankingNorm = 0;
        this.rankingHighTen = 0;
        break;
      case 2:
        this.value = 0;
        this.rankingNorm = 1;
        this.rankingHighTen = 1;
        break;
      case 3:
        this.value = 0;
        this.rankingNorm = 2;
        this.rankingHighTen = 2;
        break;
      case 4:
        this.value = 2;
        this.rankingNorm = 3;
        this.rankingHighTen = 4;
        break;
      case 5:
        this.value = 3;
        this.rankingNorm = 4;
        this.rankingHighTen = 5;
        break;
      case 6:
        this.value = 4;
        this.rankingNorm = 5;
        this.rankingHighTen = 6;
        break;
      case 7:
        this.value = 10;
        this.rankingNorm = 6;
        this.rankingHighTen = 3;
        break;
      case 8:
        this.value = 11;
        this.rankingNorm = 7;
        this.rankingHighTen = 7;
        break;
    }
  }

  public int getValue() {
    return this.value;
  }

  public int getRankingNorm() {
    return this.rankingNorm;
  }

  public int getRankingHighTen() {
    return this.rankingHighTen;
  }
}
