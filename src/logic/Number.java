package logic;

public enum Number {
  SEVEN(1), EIGHT(2), NINE(3), JACK(4), QUEEN(5), KING(6), TEN(7), ASS(8);
  private int value;
  private int rankingNorm;
  private int rankingLowTen;
  private int matadorValue;

  private Number(int i) {
    switch (i) {
      case 1:
        this.value = 0;
        this.rankingNorm = 0;
        this.rankingLowTen = 0;
        this.matadorValue = 10;
        break;
      case 2:
        this.value = 0;
        this.rankingNorm = 1;
        this.rankingLowTen = 1;
        this.matadorValue = 9;
        break;
      case 3:
        this.value = 0;
        this.rankingNorm = 2;
        this.rankingLowTen = 2;
        this.matadorValue = 8;
        break;
      case 4:
        this.value = 2;
        this.rankingNorm = 3;
        this.rankingLowTen = 4;
        this.matadorValue = 0;
        break;
      case 5:
        this.value = 3;
        this.rankingNorm = 4;
        this.rankingLowTen = 5;
        this.matadorValue = 7;
        break;
      case 6:
        this.value = 4;
        this.rankingNorm = 5;
        this.rankingLowTen = 6;
        this.matadorValue = 6;
        break;
      case 7:
        this.value = 10;
        this.rankingNorm = 6;
        this.rankingLowTen = 3;
        this.matadorValue = 5;
        break;
      case 8:
        this.value = 11;
        this.rankingNorm = 7;
        this.rankingLowTen = 7;
        this.matadorValue = 4;
        break;
    }
  }

  public int getValue() {
    return this.value;
  }

  public int getRankingNorm() {
    return this.rankingNorm;
  }

  public int getRankingLowTen() {
    return this.rankingLowTen;
  }
  
  public int getMatadorValue() {
    return this.matadorValue;
  }
}
