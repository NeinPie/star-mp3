package visual.controller;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javafx.scene.control.Button;
import logic.Mp3Player;
import visual.views.MainView;

import java.io.IOException;


public class MainController extends ControllerBase<MainView> {
    private Mp3Player player;
    private Button playButton;

    public MainController(Mp3Player player) {
        super();
        root = new MainView();

        this.player = player;
        //init();
    }

    @Override
    public void init() {
        playButton.setOnAction(event -> {
            try {
                player.playSong("02 Drei Worte",0);
            } catch (InvalidDataException e) {
                throw new RuntimeException(e);
            } catch (UnsupportedTagException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
