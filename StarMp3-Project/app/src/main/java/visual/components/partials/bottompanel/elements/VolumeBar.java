package visual.components.partials.bottompanel.elements;

import javafx.animation.PauseTransition;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.stage.Popup;
import javafx.util.Duration;

public class VolumeBar extends StackPane {

    private final Button volumeButton;
    private final Slider volumeSlider;
    private final Popup popup;

    private final StackPane sliderContainer;

    private final double defaultVolume = 0.7;
    private double lastVolume = defaultVolume;

    public VolumeBar() {

        // button
        volumeButton = new Button();
        volumeButton.setId("volume-button");
        volumeButton.getStyleClass().add("icon-button");

        getChildren().add(volumeButton);

        // slider
        volumeSlider = new Slider(0, 1, defaultVolume);
        volumeSlider.setOrientation(Orientation.VERTICAL);
        volumeSlider.setPrefHeight(120);
        volumeSlider.setId("volume-slider");

        volumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
             if (newVal.doubleValue() == 0) {
                 updateVolumeIcon(true);
             } else {
                 updateVolumeIcon(false);
                 lastVolume = newVal.doubleValue();
             }
            });

        // container for slider
        sliderContainer = new StackPane();
        sliderContainer.getStyleClass().add("volume-slider-container");
        sliderContainer.setPrefSize(50, 140);

        sliderContainer.getChildren().add(volumeSlider);

        // popup
        popup = new Popup();
        popup.setAutoHide(true);
        popup.getContent().add(sliderContainer);

        // click
        PauseTransition clickTimer = new PauseTransition(Duration.millis(250));
        final boolean[] isDoubleClick = {false};

        volumeButton.setOnMouseClicked(event -> {
            if (event.getButton() != MouseButton.PRIMARY) return;

            if (event.getClickCount() == 2) {
                isDoubleClick[0] = true;
                muteToggle();
            } else {
                isDoubleClick[0] = false;
                clickTimer.setOnFinished(e -> {
                    if (!isDoubleClick[0]) togglePopup();
                });
                clickTimer.playFromStart();
            }
        });
    }

    private void togglePopup() {
        if (popup.isShowing()) {
            popup.hide();
            return;
        } 

        var bounds = volumeButton.localToScreen(volumeButton.getBoundsInLocal());

        double buttonCenterX = bounds.getMinX() + bounds.getWidth() / 2;

        double popupX = buttonCenterX - sliderContainer.getPrefWidth() / 2;
        double popupY = bounds.getMinY() - sliderContainer.getPrefHeight() - 6;

        popup.show(volumeButton, popupX, popupY);
    }

    private void muteToggle() {
        if (volumeSlider.getValue() > 0) {
            lastVolume = volumeSlider.getValue();
            volumeSlider.setValue(0);
            updateVolumeIcon(true);
        } else {
            volumeSlider.setValue(lastVolume);
            updateVolumeIcon(false);
        }
    }

    public void updateVolumeIcon(boolean muted) {
        if (muted) {
            if (!volumeButton.getStyleClass().contains("muted")) {
                volumeButton.getStyleClass().add("muted");
            }
        } else {
            volumeButton.getStyleClass().remove("muted");
        }
    }

    public DoubleProperty volumeProperty() {
        return volumeSlider.valueProperty();
    }

    public void bindVolume(DoubleProperty externalVolume) {
        volumeSlider.valueProperty().bindBidirectional(externalVolume);
    }

    public double getDefaultVolume() {
        return defaultVolume;
    }

    public Button getButton() {
    return volumeButton;
    }
}

