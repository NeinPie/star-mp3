package visual.components.overview;

import logic.Mp3Player;
import visual.components.ControllerBase;
import visual.components.partials.bottompanel.BottomPanelController;

public class SongOverviewController extends ControllerBase<SongOverview> {
    private Mp3Player player;
    public SongOverviewController(Mp3Player mp3Player) {
        super();
        root = new SongOverview();

        this.player = mp3Player;

        init();
    }

    @Override
    public void init() {
        ControllerBase bottomPanelController = new BottomPanelController(player);
        root.setBottomPanel(bottomPanelController.getRoot());
    }
}
