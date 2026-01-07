package logic.exception;

/**
 * FileReadException wird geworfen, wenn es Fehler beim Einlesen einer Datei gibt.
 */
public class FileReadException extends ExceptionBase {
  public FileReadException(String message) {
    super("Beim Einlesen ist ein Fehler aufgetreten",message);
  }
  public FileReadException(String problem, String message) {
    super(problem, message);
  }
  public FileReadException() {
    super("Beim Einlesen ist ein Fehler aufgetreten");
  }
}