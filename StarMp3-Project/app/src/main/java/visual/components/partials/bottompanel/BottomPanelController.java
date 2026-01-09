package visual.components.partials.bottompanel;

import javafx.scene.control.Button;
import logic.Mp3Player;
import visual.components.ControllerBase;

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
        Button playButton = controlBar.getPlayButton();
        Button skipButton = controlBar.getNextButton();
        Button backButton = controlBar.getBackButton();
        Button shuffleButton = controlBar.getShuffleButton();
        Button repeatButton = controlBar.getRepeatButton();

        playButton.setOnAction(e -> System.out.println("Playbutton clicked"));
        skipButton.setOnAction(e -> System.out.println("Skipbutton clicked"));
        backButton.setOnAction(e -> System.out.println("Back button clicked"));
        shuffleButton.setOnAction(e -> System.out.println("Shuffle button clicked"));
        repeatButton.setOnAction(e -> System.out.println("Repeat button clicked"));
        volumeButton.setOnAction(e -> System.out.println("Volume button clicked"));
        likeButton.setOnAction(e -> System.out.println("Like button clicked"));
        progressBar.setOnMouseClicked(e -> System.out.println("Progress button clicked"));
    }
}
