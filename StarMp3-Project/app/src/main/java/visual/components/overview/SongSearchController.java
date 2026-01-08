package visual.components.overview;

import logic.Mp3Player;
import visual.components.ControllerBase;

public class SongSearchController extends ControllerBase {
    private Mp3Player player;
    public SongSearchController(Mp3Player mp3Player) {
        this.player = mp3Player;
    }

    @Override
    public void init() {

    }
}
