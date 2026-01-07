package logic.exception;

/**
 * DirectoryNotExistsException wird geworfen, wenn versucht wird, auf ein nicht verfügbares Verzeichnis zuzugreifen.
 */
public class DirectoryNotExistsException extends ExceptionBase {
  public DirectoryNotExistsException(String message) {
    super("Verzeichnis existiert nicht",message);
  }
  public DirectoryNotExistsException(String problem, String message) {
    super(problem, message);
  }
  public DirectoryNotExistsException() {
    super("Verzeichnis existiert nicht");
  }
}