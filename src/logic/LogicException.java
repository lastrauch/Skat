package logic;

public class LogicException extends Exception {

  public LogicException(String message) {
    super("Logic exception: " + message);
  }
}
