package logic.exception;

/**
 * PlaylistNotExistsException wird geworfen, wenn versucht wird, auf eine nicht verfügbare Playlist zuzugreifen.
 */
public class PlaylistNotExistsException extends ExceptionBase {
    public PlaylistNotExistsException(String message) {
        super("Playlist existiert nicht",message);
    }
    public PlaylistNotExistsException(String problem, String message) {
        super(problem, message);
    }
    public PlaylistNotExistsException() {
        super("Playlist existiert nicht");
    }
}