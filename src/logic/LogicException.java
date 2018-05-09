package logic;

public class LogicException extends Exception {

  private static final long serialVersionUID = 1L;

  public LogicException(String message) {
    super("Logic exception: " + message);
  }
}
