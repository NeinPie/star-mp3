package visual.components.partials.bottompanel;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import logic.Mp3Player;
import logic.handler.M3uHandler;
import visual.components.ControllerBase;
import visual.components.partials.bottompanel.elements.ControlBar;
import visual.components.partials.bottompanel.elements.ProgressBar;

import java.io.IOException;

public class BottomPanelController extends ControllerBase<BottomPanel> {
    private Mp3Player player;
    private ControlBar controlBar;
    private Button volumeButton;
    private ToggleButton likeButton;
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

        playButton.setOnAction(e -> {
            if(playButton.isSelected()){
                if(player.getCurrentSong() != null){
                    System.out.println("Continue playing");
                    player.play();
                } else {
                    //TODO Info für Nutzer das aus allen Random gespielt wird
                    player.playPlaylist("AllSongs");
                }
            } else {
                System.out.println("pause");
                player.pause();
            }
        });
    }

    private void initSkipButton(){
        Button skipButton = controlBar.getNextButton();

        skipButton.setOnAction(e -> {
            player.skipSong();
        });
    }

    private void initBackButton(){
        Button backButton = controlBar.getBackButton();

        backButton.setOnAction(e -> {
            player.skipSongBack();
        });
    }

    private void initShuffleButton(){
        ToggleButton shuffleButton = controlBar.getShuffleButton();
        shuffleButton.setOnAction(e -> {
            if(shuffleButton.isSelected()){
                player.mixQueue();
            } else {
                player.unmixQueue();
            }
        });
    }

    private void initRepeatButton(){
        ToggleButton repeatButton = controlBar.getRepeatButton();

        repeatButton.setOnAction(e -> System.out.println("Repeat button clicked (auch noch nicht fertig)"));
    }

    private void initVolumeButton(){
        volumeButton.setOnAction(e -> System.out.println("Volume button clicked"));
    }

    private void initLikeButton(){
        likeButton.setOnAction(e -> {
            if(player.getCurrentSong() != null) {
                likeButton.setSelected(M3uHandler.inPlaylist("favorites", player.getCurrentSong().getTITLE()));

                if (likeButton.isSelected()) {
                    System.out.println("UnLike button aktiv");
                    player.removeSongFromFavorites();
                } else {
                    System.out.println("like button aktiv");
                    player.addSongToFavorites();
                }
            } else {
                //TODO: Fehler wenn kein song gewählt
            }
        });
    }

    private void initProgressBar(){
        progressBar.getSlider().valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);
        });
    }
}
