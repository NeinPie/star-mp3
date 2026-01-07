package logic.exception;
/**
 * NoSongToInteractWithException wird geworfen, wenn Befehle ausgeführt werden, die einen Song benötigen, ohne einen solchen zu haben
 */
public class NoSongToInteractWithException extends ExceptionBase {
    public NoSongToInteractWithException(String message) {
        super("Keinen Song gewählt",message);
    }
    public NoSongToInteractWithException(String problem, String message) {
        super(problem, message);
    }
    public NoSongToInteractWithException() {
        super("Es gibt keinen Song, auf den du diese Aktion anwenden könntest");
    }
}