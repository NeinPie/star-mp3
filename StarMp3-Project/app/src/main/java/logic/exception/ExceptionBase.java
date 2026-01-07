package logic.exception;

import javafx.scene.control.Alert;
import visual.views.partials.Mp3Alert;

/**
 * ExceptionBase dient als Basis für alle geworfenen Exceptions im Programm. Bei Auftritt wird ein MP3Alert ausgelöst, der dem Nutzer angezeigt wird.
 */
public abstract class ExceptionBase extends RuntimeException {
    String problem;

    public ExceptionBase(String problem, String message){
        super(message);
        this.problem = problem;
        //new Mp3Alert(Alert.AlertType.ERROR, "Exception", problem, message, 3000);
    }

    public ExceptionBase(String message) {
        this("Exception wurde geworfen", message);
    }
    public ExceptionBase(){
        this("Bei Deiner gewünschten Aktion ist ein Fehler aufgetreten.");
    }

}