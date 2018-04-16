package ai;

import java.io.Serializable;

public class DegreeOFDifficulty implements Serializable{
  private static final long serialVersionUID = 1L;
  
  private AIDegreeOfDifficulty degree;
  
  
  public DegreeOFDifficulty(AIDegreeOfDifficulty degree) {
    this.degree = degree;
    
  }
  public AIDegreeOfDifficulty getDifficulty() {
    return degree;
  }
  
  
  
  
  

}
