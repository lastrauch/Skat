package network;

public class Server {
  
  private int test1;
  private String test2;
  
  public void Server(int test1, String test2){
    this.test1 = test1;
    this.test2 = test2;
  }
  
  private void setTest1(int test1){
    this.test1 = test1;
  }
  
  private int getTest1(){
    return this.test1;
  }
}
