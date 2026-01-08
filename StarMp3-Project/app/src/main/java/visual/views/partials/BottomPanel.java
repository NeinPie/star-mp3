package visual.views.partials;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class BottomPanel extends BorderPane {

    public BottomPanel() {
        getStyleClass().add("bottom-bar");
        setPrefHeight(100);
        setMinHeight(100);
        setMaxHeight(100);

        ProgressBar progressBar = new ProgressBar();
        ControlBar controls = new ControlBar();

        LikeButton likeButton = new LikeButton();
        VolumeButton volumeButton = new VolumeButton();

        //fortschrittsleiste
        VBox songControl = new VBox(10);
        songControl.getChildren().addAll(progressBar, controls);
        songControl.setAlignment(Pos.CENTER);

        setCenter(songControl);
        BorderPane.setAlignment(songControl, Pos.CENTER);

        //like button
        setLeft(likeButton);
        BorderPane.setAlignment(likeButton, Pos.CENTER_LEFT);
        BorderPane.setMargin(likeButton, new Insets(0, 0, 0, 30));

        //lautstärke button
        setRight(volumeButton);
        BorderPane.setAlignment(volumeButton, Pos.CENTER_RIGHT);
        BorderPane.setMargin(volumeButton, new Insets(0, 30, 0, 0));
    }
}

