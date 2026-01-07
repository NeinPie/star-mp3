package logic.exception;

/**
 * PlayException wird geworfen, wenn es Fehler beim Abspielen eines Songs gibt
 */
public class PlayException extends ExceptionBase {
    public PlayException(String message) {
        super("Beim Abspielen ist ein Fehler aufgetreten",message);
    }
    public PlayException(String problem, String message) {
        super(problem, message);
    }
    public PlayException() {
        super("Beim Abspielen ist ein Fehler aufgetreten");
    }
}