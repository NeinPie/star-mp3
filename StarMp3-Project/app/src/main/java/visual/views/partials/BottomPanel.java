package visual.views.partials;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class BottomPanel extends VBox{

    public BottomPanel() {
        getStyleClass().add("bottom-bar");
        setPrefHeight(100);
        setMinHeight(100);
        setMaxHeight(100);

        ProgressBar progressBar = new ProgressBar();
        ControlBar controls = new ControlBar();
        VBox progressWrapper = new VBox(progressBar);
        progressWrapper.setPadding(new Insets(50, 0, 0, 0));
        progressWrapper.setAlignment(Pos.TOP_CENTER);


        LikeButton likeButton = new LikeButton();
        VolumeButton volumeButton = new VolumeButton();

        BorderPane bottomRow = new BorderPane();
        bottomRow.setLeft(likeButton);
        bottomRow.setCenter(controls);
        bottomRow.setRight(volumeButton);

        bottomRow.setMaxWidth(Double.MAX_VALUE);
        bottomRow.setPadding(new Insets(0, 0, 30, 0));

        BorderPane.setAlignment(likeButton, Pos.CENTER_LEFT);
        BorderPane.setAlignment(volumeButton, Pos.CENTER_RIGHT);

        setAlignment(Pos.CENTER);
        setFillWidth(true);

        getChildren().addAll(progressWrapper, bottomRow);
    }
}

