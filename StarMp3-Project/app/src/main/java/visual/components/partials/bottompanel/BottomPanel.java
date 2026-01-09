package visual.components.partials.bottompanel;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import visual.components.partials.bottompanel.elements.ControlBar;
import visual.components.partials.bottompanel.elements.LikeButton;
import visual.components.partials.bottompanel.elements.ProgressBar;
import visual.components.partials.bottompanel.elements.VolumeButton;

public class BottomPanel extends BorderPane {
    ControlBar controls;
    Button volumeButton;
    ToggleButton likeButton;
    ProgressBar progressBar;

    public BottomPanel() {
        controls = new ControlBar();

        volumeButton = new VolumeButton();
        likeButton = new LikeButton();
        progressBar = new ProgressBar();

        getStyleClass().add("bottom-bar");
        setPrefHeight(100);
        setMinHeight(100);
        setMaxHeight(100);

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

