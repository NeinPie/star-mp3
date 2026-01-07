package logic.exception;

/**
 * FileWriteException wird geworfen, wenn das Schreiben in eine Datei misslingt
 */
public class FileWriteException extends ExceptionBase {
  public FileWriteException(String message) {
    super("Beim Schreiben einer Datei ist ein Fehler aufgetreten",message);
  }
  public FileWriteException(String problem, String message) {
    super(problem, message);
  }
  public FileWriteException() {
    super("Beim Schreiben einer Datei ist ein Fehler aufgetreten");
  }
}