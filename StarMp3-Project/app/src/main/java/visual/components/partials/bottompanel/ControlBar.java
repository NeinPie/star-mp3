package visual.components.partials.bottompanel;

import javafx.beans.property.BooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ControlBar extends HBox {


    private final Button nextButton;
    private final Button playButton;
    private final Button backButton;
    private final Button repeatButton;
    private final Button shuffleButton;

    private BooleanProperty playingProperty;

    public ControlBar() {

        getStyleClass().add("control-bar");
        setSpacing(12);
        setAlignment(Pos.CENTER);

        repeatButton = new Button();
        repeatButton.setId("repeat-button");
        repeatButton.getStyleClass().addAll("icon-button-small", "repeatOFF");

        backButton = new Button();
        backButton.setId("back-button");
        backButton.getStyleClass().addAll("icon-button-small", "back");

        playButton = new Button();
        playButton.setId("play-button");
        playButton.getStyleClass().addAll("icon-button-small", "play");

        playButton.setOnAction(e -> {
            if (playingProperty != null) {
                playingProperty.set(!playingProperty.get());
            }
        });

        nextButton = new Button();
        nextButton.setId("next-button");
        nextButton.getStyleClass().addAll("icon-button-small", "next");

        shuffleButton = new Button();
        shuffleButton.setId("shuffle-button");
        shuffleButton.getStyleClass().addAll("icon-button-small", "shuffleOFF");

        getChildren().addAll(
                repeatButton, backButton, playButton, nextButton, shuffleButton
        );
    }

    public void bindPlayState(BooleanProperty playing) {
        this.playingProperty = playing;
        playing.addListener((obs, oldV, newV) -> setPlaying(newV));
    }

    public void setPlaying(boolean playing) {
    playButton.getStyleClass().removeAll("play", "pause");
    playButton.getStyleClass().add(playing ? "pause" : "play");
    }

    public void setRepeatState(boolean on) {
        repeatButton.getStyleClass().removeAll("repeatON", "repeatOFF");
        repeatButton.getStyleClass().add(on ? "repeatON" : "repeatOFF");
    }

    public void setShuffleState(boolean on) {
        shuffleButton.getStyleClass().removeAll("shuffleON", "shuffleOFF");
        shuffleButton.getStyleClass().add(on ? "shuffleON" : "shuffleOFF");
    }

    public void bindRepeat(BooleanProperty repeat) {
        repeat.addListener((o, oldV, newV) -> setRepeatState(newV));
    }

    public void bindShuffle(BooleanProperty shuffle) {
        shuffle.addListener((o, oldV, newV) -> setShuffleState(newV));
    }

    public Button getPlayButton() { return playButton; }
    public Button getNextButton() { return nextButton; }
    public Button getBackButton() { return backButton; }
    public Button getRepeatButton() { return repeatButton; }
    public Button getShuffleButton() { return shuffleButton; }
}
