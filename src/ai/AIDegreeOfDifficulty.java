package ai;

public enum AIDegreeOfDifficulty {
  EASY("easy"), NORMAL("normal"), HARD("hard");
  
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
