package ai;

public enum AIDegreeOfDifficulty {
  VERY_EASY("very easy"), EASY("easy"), NORMAL("normal"), HARD("hard"), VERY_HARD("very hard");
  
  private AIDegreeOfDifficulty getDegree(String degree) {
    for ( AIDegreeOfDifficulty u : AIDegreeOfDifficulty.class.getEnumConstants() ) {
      if ( u.degreeOfDiff.equalsIgnoreCase(degree) ) {
          return u;
      }
  }
  return null;
}
    
  
private String degreeOfDiff;



private AIDegreeOfDifficulty(String degreeOfDiff) {
    this.degreeOfDiff = degreeOfDiff;
}

}
