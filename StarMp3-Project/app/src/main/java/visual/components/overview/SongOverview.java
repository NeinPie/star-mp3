package visual.components.overview;

import javafx.scene.control.Label;
import visual.BorderPaneWithBottomPanelBase;


public class SongOverview extends BorderPaneWithBottomPanelBase {

    public SongOverview() {
        setId("overviewRoot");
        setCenter(new Label("Hier sollte die SongOverview rein und irgendwo da drin brauchen wir ein ListPane"));
    }
}
