package visual.views.partials;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class BottomPanel extends VBox{

    public BottomPanel() {
        getStyleClass().add("bottom-bar");

        ProgressBar progressBar = new ProgressBar();
        ControlBar controls = new ControlBar();

        LikeButton likeButton = new LikeButton();
        VolumeButton volumeButton = new VolumeButton();

        BorderPane bottomRow = new BorderPane();
        bottomRow.setLeft(likeButton);
        bottomRow.setCenter(controls);
        bottomRow.setRight(volumeButton);

        bottomRow.setMaxWidth(Double.MAX_VALUE);

        BorderPane.setAlignment(likeButton, Pos.CENTER_LEFT);
        BorderPane.setAlignment(volumeButton, Pos.CENTER_RIGHT);

        setSpacing(8);
        setAlignment(Pos.CENTER);
        setFillWidth(true);

        getChildren().addAll(progressBar, bottomRow);
    }
}

