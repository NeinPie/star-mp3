package logic.exception;

/**
 * InvalidValueException wird geworfen, wenn in einer Funktion ungültige Werte angegeben werden
 */
public class InvalidValueException extends ExceptionBase {
  public InvalidValueException(String message) {
    super("Fehlerhafter Wert",message);
  }
  public InvalidValueException(String problem, String message) {
    super(problem, message);
  }
  public InvalidValueException() {
    super("Fehlerhafter Wert");
  }
}