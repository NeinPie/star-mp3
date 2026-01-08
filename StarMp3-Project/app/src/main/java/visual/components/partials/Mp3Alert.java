package visual.components.partials;

import javafx.animation.PauseTransition;
import javafx.scene.control.Alert;
import javafx.util.Duration;

/**
 * MP3Alert Benachrichtigt den Nutzer grafisch über etwas
 */
public class Mp3Alert extends Alert {
    public Mp3Alert(AlertType alertType, String title, String headerText, String info, int showDuration) {
        super(alertType);
        super.setTitle(title);
        super.setHeaderText(headerText);
        super.setContentText(info);

        super.show();

        /**
         * Anzeigedauer
         */
        PauseTransition delay = new PauseTransition(Duration.millis(showDuration));
        delay.setOnFinished(event -> this.hide());
        delay.play();
    }
}