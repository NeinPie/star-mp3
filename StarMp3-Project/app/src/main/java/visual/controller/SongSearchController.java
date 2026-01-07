package visual.controller;

import logic.Mp3Player;

public class SongSearchController extends ControllerBase{
    private Mp3Player player;
    public SongSearchController(Mp3Player mp3Player) {
        this.player = mp3Player;
    }

    @Override
    public void init() {

    }
}
