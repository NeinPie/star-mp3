package logic.exception;

/**
 * SongNotExistsException wird geworfen, wenn versucht wird, auf einen nicht verfügbaren Song zuzugreifen.
 */
public class SongNotExistsException extends ExceptionBase {
    public SongNotExistsException(String message) {
        super("Song existiert nicht",message);
    }
    public SongNotExistsException(String problem, String message) {
        super(problem, message);
    }
    public SongNotExistsException() {
        super("Song existiert nicht");
    }
}