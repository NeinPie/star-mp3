package visual.components.partials.bottompanel;

import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import logic.Mp3Player;
import visual.components.ControllerBase;
import visual.components.partials.bottompanel.elements.ControlBar;
import visual.components.partials.bottompanel.elements.ProgressBar;

public class BottomPanelController extends ControllerBase<BottomPanel> {
    private Mp3Player player;
    private ControlBar controlBar;
    private Button volumeButton;
    private Button likeButton;
    private ProgressBar progressBar;

    public BottomPanelController(Mp3Player player) {
        super();
        root = new BottomPanel();

        this.player = player;
        controlBar = root.controls;
        volumeButton = root.volumeButton;
        likeButton = root.likeButton;
        progressBar = root.progressBar;

        init();
    }
    @Override
    public void init() {
        initPlayButton();
        initSkipButton();
        initBackButton();
        initShuffleButton();
        initRepeatButton();
        initVolumeButton();
        initLikeButton();
        initProgressBar();
    }

    private void initPlayButton(){
        ToggleButton playButton = controlBar.getPlayButton();

        playButton.setOnAction(e -> System.out.println("Playbutton clicked"));
    }

    private void initSkipButton(){
        Button skipButton = controlBar.getNextButton();

        skipButton.setOnAction(e -> System.out.println("Skipbutton clicked"));
    }

    private void initBackButton(){
        Button backButton = controlBar.getBackButton();

        backButton.setOnAction(e -> System.out.println("Back button clicked"));
    }

    private void initShuffleButton(){
        ToggleButton shuffleButton = controlBar.getShuffleButton();

        shuffleButton.setOnAction(e -> System.out.println("Shuffle button clicked"));
    }

    private void initRepeatButton(){
        ToggleButton repeatButton = controlBar.getRepeatButton();

        repeatButton.setOnAction(e -> System.out.println("Repeat button clicked"));
    }

    private void initVolumeButton(){
        volumeButton.setOnAction(e -> System.out.println("Volume button clicked"));
    }

    private void initLikeButton(){
        likeButton.setOnAction(e -> System.out.println("Like button clicked"));
    }

    private void initProgressBar(){
        progressBar.getSlider().valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);
        });
    }
}
